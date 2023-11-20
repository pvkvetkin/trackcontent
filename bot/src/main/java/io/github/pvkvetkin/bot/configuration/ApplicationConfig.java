package io.github.pvkvetkin.bot.configuration;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "tgbot", ignoreUnknownFields = false)
public record ApplicationConfig(
    @NotNull TgBot bot,
    @NotNull Scrapper scrapper,
    @NotNull Rabbitmq rabbitmq,
    @NotNull String oltpReceiverUrl
) {

    record TgBot(String token) {
    }

    record Scrapper(String url) {
    }

    public record Rabbitmq(String exchangeName, String queueName) {
    }
}
