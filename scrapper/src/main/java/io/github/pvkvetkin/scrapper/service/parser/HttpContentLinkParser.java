package io.github.pvkvetkin.scrapper.service.parser;

import io.github.pvkvetkin.linkparser.ContentLinkParser;
import io.github.pvkvetkin.linkparser.dto.GithubLinkDto;
import io.github.pvkvetkin.linkparser.dto.LinkDto;
import io.github.pvkvetkin.linkparser.dto.StackOverFlowLinkDto;
import io.github.pvkvetkin.scrapper.client.GithubWebClient;
import io.github.pvkvetkin.scrapper.client.StackoverflowWebClient;
import io.github.pvkvetkin.scrapper.dto.response.ContentResponse;
import io.github.pvkvetkin.scrapper.entity.LinkType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HttpContentLinkParser {

    private final ContentLinkParser linkParser;
    private final GithubWebClient githubWebClient;
    private final StackoverflowWebClient stackoverflowWebClient;

    public ContentResponse parseLink(String url) {
        LinkType type = parseLinkType(url);
        LinkDto parseLink = linkParser.parseLink(url);
        return switch (type) {
            case GITHUB -> {
                GithubLinkDto githubLinkDto = (GithubLinkDto) parseLink;
                yield githubWebClient.fetchRepository(githubLinkDto.username(), githubLinkDto.repository());
            }
            case STACKOVERFLOW -> {
                StackOverFlowLinkDto stackOverFlowLinkDto = (StackOverFlowLinkDto) parseLink;
                yield stackoverflowWebClient.fetchRepository(stackOverFlowLinkDto.id());
            }
        };
    }

    public LinkType parseLinkType(String url) {
        LinkDto parseLink = linkParser.parseLink(url);
        if (parseLink instanceof GithubLinkDto) {
            return LinkType.GITHUB;
        } else if (parseLink instanceof StackOverFlowLinkDto) {
            return LinkType.STACKOVERFLOW;
        }
        return null;
    }
}
