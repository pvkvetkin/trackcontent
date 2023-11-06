package io.github.pvkvetkin.scrapper.service.jooq;

import io.github.pvkvetkin.scrapper.exception.DuplicateChatIdException;
import io.github.pvkvetkin.scrapper.exception.TgChatIdNotFound;
import io.github.pvkvetkin.scrapper.repository.JooqChatRepository;
import io.github.pvkvetkin.scrapper.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class JooqChatService implements ChatService {

    private final JooqChatRepository chatRepository;

    @Override
    public void register(Long chatId) {
        try {
            chatRepository.add(chatId);
        } catch (DuplicateKeyException e) {
            throw new DuplicateChatIdException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public void unregister(Long chatId) {
        try {
            chatRepository.remove(chatId);
        } catch (EmptyResultDataAccessException e) {
            throw new TgChatIdNotFound(e.getMessage(), e);
        }
    }
}
