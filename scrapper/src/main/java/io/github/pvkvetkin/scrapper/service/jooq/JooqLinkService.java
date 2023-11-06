package io.github.pvkvetkin.scrapper.service.jooq;

import io.github.pvkvetkin.scrapper.dto.LinkType;
import io.github.pvkvetkin.scrapper.dto.request.LinkUpdateRequest;
import io.github.pvkvetkin.scrapper.dto.response.ContentResponse;
import io.github.pvkvetkin.scrapper.dto.response.GithubResponse;
import io.github.pvkvetkin.scrapper.dto.response.LinkResponse;
import io.github.pvkvetkin.scrapper.dto.response.StackoverflowResponse;
import io.github.pvkvetkin.scrapper.entity.Link;
import io.github.pvkvetkin.scrapper.exception.InvalidUrlException;
import io.github.pvkvetkin.scrapper.exception.LinkNotFoundException;
import io.github.pvkvetkin.scrapper.repository.JooqLinkRepository;
import io.github.pvkvetkin.scrapper.service.LinkService;
import io.github.pvkvetkin.scrapper.service.parser.HttpContentLinkParser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;

@Service
@AllArgsConstructor
public class JooqLinkService implements LinkService {

    private final JooqLinkRepository linkRepository;
    private final HttpContentLinkParser contentLinkParser;

    private static final String LINK_NOT_FOUND_MESSAGE = "Link not found";

    @Override
    @Transactional
    public LinkResponse add(Long tgChatId, URI uri) {
        String url = extractUrl(uri);
        ContentResponse contentResponse;
        try {
            contentResponse = contentLinkParser.parseLink(url);
        } catch (IllegalArgumentException e) {
            throw new InvalidUrlException("Invalid URL: " + url, e);
        }

        LinkType type = contentResponse.type();
        Long linkId = linkRepository.add(uri, tgChatId, type);
        switch (type) {
            case GITHUB -> {
                GithubResponse ghResponse = (GithubResponse) contentResponse;
                linkRepository.insertGithubData(linkId, ghResponse.lastCommitAt(), ghResponse.issuesCount(), ghResponse.updatedAt());
            }
            case STACKOVERFLOW -> {
                StackoverflowResponse sofResponse = (StackoverflowResponse) contentResponse;
                linkRepository.insertStackoverflowData(linkId, sofResponse.answerCount(), sofResponse.updatedAt());
            }
            default -> throw new IllegalStateException("Unexpected type value: " + type);
        }
        return new LinkResponse(linkId, uri);
    }

    @Override
    @Transactional
    public LinkResponse remove(Long tgChatId, URI uri) {
        Link link = linkRepository.findByUrl(uri)
            .orElseThrow(() -> new LinkNotFoundException(LINK_NOT_FOUND_MESSAGE));
        linkRepository.remove(uri, tgChatId);
        return new LinkResponse(link.getId(), link.getUrl());
    }

    @Override
    public List<Link> getAllTrackedLinks(Long tgChatId) {
        return linkRepository.findAll(tgChatId);
    }

    @Override
    public List<Link> getAllNotUpdatedLinks(Long delayMinutes) {
        return linkRepository.checkUncheckedLinks(delayMinutes);
    }

    @Override
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
                return new LinkUpdateRequest(link.getId(), link.getUrl(), newResponse.toString(), linkRepository.findChatIdsByLinkIds(linkId));

            }
            default -> throw new IllegalStateException("Unexpected value: " + contentResponse.type());
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
