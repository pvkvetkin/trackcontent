package io.github.pvkvetkin.scrapper.configuration;

import io.github.pvkvetkin.linkparser.ContentLinkParser;
import io.github.pvkvetkin.linkparser.handler.GithubHandler;
import io.github.pvkvetkin.linkparser.handler.StackOverFlowHandler;
import java.time.Duration;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public record ApplicationConfig(
    @NotNull Scheduler scheduler,
    @NotNull String tgBotBaseUrl,
    @NotNull Rabbitmq rabbitmq
) {

    @Bean
    public long schedulerIntervalMs() {
        return scheduler().intervalMs().toMillis();
    }

    @Bean
    public ContentLinkParser contentLinkParser() {
        return new ContentLinkParser(new GithubHandler(new StackOverFlowHandler()));
    }

    public record Scheduler(
        Duration intervalMs
    ) {
    }

    public record Rabbitmq(
        String exchangeName,
        String queueName
    ) {
    }
}
