package io.github.pvkvetkin.bot.configuration;

import io.github.pvkvetkin.bot.client.ScrapperClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

@Configuration
public class ScrapperConfig {

    @Bean
    WebClient webClient(Builder builder, ApplicationConfig config) {
        return builder
            .baseUrl(config.scrapper().url())
            .build();
    }

    @Bean
    ScrapperClient scrapperClient(WebClient webClient) {
        return new ScrapperClient(webClient);
    }
}
