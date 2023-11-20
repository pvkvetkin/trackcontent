package io.github.pvkvetkin.scrapper.service.jooq;

import io.github.pvkvetkin.scrapper.dto.request.LinkUpdateRequest;
import io.github.pvkvetkin.scrapper.dto.response.ContentResponse;
import io.github.pvkvetkin.scrapper.dto.response.GithubResponse;
import io.github.pvkvetkin.scrapper.dto.response.LinkResponse;
import io.github.pvkvetkin.scrapper.dto.response.StackoverflowResponse;
import io.github.pvkvetkin.scrapper.entity.Link;
import io.github.pvkvetkin.scrapper.entity.LinkType;
import io.github.pvkvetkin.scrapper.exception.InvalidUrlException;
import io.github.pvkvetkin.scrapper.exception.LinkNotFoundException;
import io.github.pvkvetkin.scrapper.metric.ScrapperProcessorMetric;
import io.github.pvkvetkin.scrapper.repository.JooqLinkRepository;
import io.github.pvkvetkin.scrapper.service.LinkService;
import io.github.pvkvetkin.scrapper.service.parser.HttpContentLinkParser;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;
import io.micrometer.observation.annotation.Observed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
public class JooqLinkService implements LinkService {

    private final JooqLinkRepository linkRepository;
    private final HttpContentLinkParser contentLinkParser;
    private final ScrapperProcessorMetric processorMetric;

    private static final String LINK_NOT_FOUND_MESSAGE = "Link not found";

    @Override
    @Transactional
    @Observed
    public LinkResponse add(Long tgChatId, URI uri) {
        String url = extractUrl(uri);
        ContentResponse contentResponse;
        try {
            contentResponse = contentLinkParser.parseLink(url);
            log.info("Parsed link: {}", url);
        } catch (IllegalArgumentException e) {
            log.error("Invalid URL: {}", url);
            throw new InvalidUrlException("Invalid URL: " + url, e);
        }
        LinkType type = contentResponse.type();
        Long linkId = linkRepository.add(uri, tgChatId, type);
        switch (type) {
            case GITHUB -> {
                GithubResponse ghResponse = (GithubResponse) contentResponse;
                linkRepository.insertGithubData(linkId, ghResponse.lastCommitAt(), ghResponse.issuesCount(), ghResponse.updatedAt());
                log.info("Github link added: {}", url);
            }
            case STACKOVERFLOW -> {
                StackoverflowResponse sofResponse = (StackoverflowResponse) contentResponse;
                linkRepository.insertStackoverflowData(linkId, sofResponse.answerCount(), sofResponse.updatedAt());
                log.info("Stackoverflow link added: {}", url);
            }
            default -> {
                log.error("Unexpected type value: {}", type);
                throw new IllegalStateException("Unexpected type value: " + type);
            }
        }

        processorMetric.incrementTrackedLinks();
        return new LinkResponse(linkId, uri);
    }

    @Override
    @Transactional
    @Observed
    public LinkResponse remove(Long tgChatId, URI uri) {
        Link link = linkRepository.findByUrl(uri)
            .orElseThrow(() -> new LinkNotFoundException(LINK_NOT_FOUND_MESSAGE));
        linkRepository.remove(uri, tgChatId);
        log.info("Link successfully removed: {}", uri);
        return new LinkResponse(link.getId(), link.getUrl());
    }

    @Override
    @Observed
    public List<Link> getAllTrackedLinks(Long tgChatId) {
        log.info("Get all tracked links for chat: {}", tgChatId);
        return linkRepository.findAll(tgChatId);
    }

    @Override
    @Observed
    public List<Link> getAllNotUpdatedLinks(Long delayMinutes) {
        return linkRepository.checkUncheckedLinks(delayMinutes);
    }

    @Override
    @Observed
    public LinkUpdateRequest updateLinkData(Long linkId, ContentResponse contentResponse) {
        switch (contentResponse.type()) {
            case GITHUB -> {
                GithubResponse oldResponse = linkRepository.findGithubLinkById(linkId);
                GithubResponse newResponse = (GithubResponse) contentResponse;
                if (oldResponse.equals(newResponse)) {
                    return null;
                }

                Link link = linkRepository.findById(linkId)
                    .orElseThrow(() -> new LinkNotFoundException(LINK_NOT_FOUND_MESSAGE));
                linkRepository.updateGithubData(linkId, newResponse.lastCommitAt(), newResponse.issuesCount(), newResponse.updatedAt());

                log.info("Github link successfully updated: {}", link.getUrl());
                return new LinkUpdateRequest(link.getId(), link.getUrl(), newResponse.toString(), linkRepository.findChatIdsByLinkIds(linkId));
            }
            case STACKOVERFLOW -> {
                StackoverflowResponse oldResponse = linkRepository.findStackoverflowLinkById(linkId);
                StackoverflowResponse newResponse = (StackoverflowResponse) contentResponse;
                if (oldResponse.equals(newResponse)) {
                    return null;
                }

                Link link = linkRepository.findById(linkId)
                    .orElseThrow(() -> new LinkNotFoundException(LINK_NOT_FOUND_MESSAGE));
                linkRepository.updateStackoverflowData(linkId, newResponse.answerCount(), newResponse.updatedAt());

                log.info("Stackoverflow link successfully updated: {}", link.getUrl());
                return new LinkUpdateRequest(link.getId(), link.getUrl(), newResponse.toString(), linkRepository.findChatIdsByLinkIds(linkId));

            }
            default -> {
                log.error("Unexpected value: {}", contentResponse.type());
                throw new IllegalStateException("Unexpected value: " + contentResponse.type());
            }
        }
    }
    private String extractUrl(URI uri) {
        try {
            return uri.toURL().toString();
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error while parsing url", e);
        }
    }
}
