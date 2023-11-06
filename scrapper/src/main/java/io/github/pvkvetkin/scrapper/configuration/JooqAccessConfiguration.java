package io.github.pvkvetkin.scrapper.configuration;

import io.github.pvkvetkin.scrapper.repository.JooqChatRepository;
import io.github.pvkvetkin.scrapper.repository.JooqLinkRepository;
import io.github.pvkvetkin.scrapper.service.ChatService;
import io.github.pvkvetkin.scrapper.service.LinkService;
import io.github.pvkvetkin.scrapper.service.jooq.JooqChatService;
import io.github.pvkvetkin.scrapper.service.jooq.JooqLinkService;
import io.github.pvkvetkin.scrapper.service.parser.HttpContentLinkParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JooqAccessConfiguration {

    @Bean
    public ChatService chatService(
            JooqChatRepository chatRepository
    ) {
        return new JooqChatService(chatRepository);
    }

    @Bean
    public LinkService linkService(
            JooqLinkRepository linkRepository,
            HttpContentLinkParser linkParser
    ) {
        return new JooqLinkService(linkRepository, linkParser);
    }
}
