package io.github.pvkvetkin.bot.configuration;

import io.github.pvkvetkin.bot.client.ScrapperClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScrapperConfig {

    @Bean
    ScrapperClient scrapperClient(ApplicationConfig config) {
        return new ScrapperClient(config.scrapper().url());
    }
}
