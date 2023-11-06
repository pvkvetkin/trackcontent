package io.github.pvkvetkin.scrapper.service.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.pvkvetkin.linkparser.ContentLinkParser;
import io.github.pvkvetkin.linkparser.dto.GithubLinkDto;
import io.github.pvkvetkin.linkparser.dto.LinkDto;
import io.github.pvkvetkin.linkparser.dto.StackOverFlowLinkDto;
import io.github.pvkvetkin.scrapper.client.GithubWebClient;
import io.github.pvkvetkin.scrapper.client.StackoverflowWebClient;
import io.github.pvkvetkin.scrapper.dto.LinkType;
import io.github.pvkvetkin.scrapper.dto.response.ContentResponse;
import io.github.pvkvetkin.scrapper.dto.response.GithubResponse;
import io.github.pvkvetkin.scrapper.dto.response.StackoverflowResponse;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@ComponentScan("io.github.pvkvetkin.scrapper.configuration.ApplicationConfig")
@RequiredArgsConstructor
public class HttpContentLinkParser {

    private final ContentLinkParser linkParser;
    private final ObjectMapper objectMapper;

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
        if (parseLink instanceof GithubLinkDto githubLinkDto) {
            return LinkType.GITHUB;
        } else if (parseLink instanceof StackOverFlowLinkDto stackOverFlowLinkDto) {
            return LinkType.STACKOVERFLOW;
        }
        return null;
    }

    private String parseResponseToString(ContentResponse response) {
        try {
            return objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<GithubResponse> parseGithubDataToResponse(String content) {
        try {
            return Optional.ofNullable(objectMapper.readValue(content, GithubResponse.class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<StackoverflowResponse> parseStackoverflowDataToResponse(String content) {
        return Optional.ofNullable(objectMapper.convertValue(content, StackoverflowResponse.class));
    }
}
