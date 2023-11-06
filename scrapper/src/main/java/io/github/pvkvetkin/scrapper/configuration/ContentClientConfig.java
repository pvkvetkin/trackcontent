package io.github.pvkvetkin.scrapper.configuration;

import io.github.pvkvetkin.scrapper.client.GithubWebClient;
import io.github.pvkvetkin.scrapper.client.StackoverflowWebClient;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@ConfigurationProperties(prefix = "client", ignoreUnknownFields = false)
public record ContentClientConfig(
    @NotNull String githubUrl,
    @NotNull String stackoverflowUrl
) {

    @Bean
    public GithubWebClient githubWebClient() {
        return new GithubWebClient(githubUrl);
    }

    @Bean
    public StackoverflowWebClient stackoverflowWebClient() {
        return new StackoverflowWebClient(stackoverflowUrl);
    }
}
