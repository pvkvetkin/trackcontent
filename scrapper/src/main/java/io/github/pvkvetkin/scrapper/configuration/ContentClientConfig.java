package io.github.pvkvetkin.scrapper.configuration;

import io.github.pvkvetkin.scrapper.client.GithubWebClient;
import io.github.pvkvetkin.scrapper.client.StackoverflowWebClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@ConfigurationProperties(prefix = "client", ignoreUnknownFields = false)
public record ContentClientConfig(
    Github github,
    Stackoverflow stackoverflow
) {

    @Bean
    public GithubWebClient githubWebClient() {
        return new GithubWebClient(github.githubUrl, github().githubToken);
    }

    @Bean
    public StackoverflowWebClient stackoverflowWebClient() {
        return new StackoverflowWebClient(stackoverflow.stackoverflowUrl);
    }

    public record Github(
        String githubUrl,
        String githubToken
    ) {
    }

    public record Stackoverflow(
        String stackoverflowUrl
    ) {
    }
}
