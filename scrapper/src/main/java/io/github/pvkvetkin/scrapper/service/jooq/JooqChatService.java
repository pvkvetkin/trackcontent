package io.github.pvkvetkin.scrapper.service.jooq;

import io.github.pvkvetkin.scrapper.exception.DuplicateChatIdException;
import io.github.pvkvetkin.scrapper.exception.TgChatIdNotFound;
import io.github.pvkvetkin.scrapper.repository.JooqChatRepository;
import io.github.pvkvetkin.scrapper.service.ChatService;
import io.micrometer.observation.annotation.Observed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
public class JooqChatService implements ChatService {

    private final JooqChatRepository chatRepository;

    @Override
    @Observed
    public void register(Long chatId) {
        try {
            chatRepository.add(chatId);
            log.info("Chat with id {} registered", chatId);
        } catch (DuplicateKeyException e) {
            log.error("Chat with id {} already registered", chatId);
            throw new DuplicateChatIdException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    @Observed
    public void unregister(Long chatId) {
        try {
            chatRepository.remove(chatId);
            log.info("Chat with id {} unregistered", chatId);
        } catch (EmptyResultDataAccessException e) {
            log.error("Chat with id {} not registered", chatId);
            throw new TgChatIdNotFound(e.getMessage(), e);
        }
    }
}
