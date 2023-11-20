package io.github.pvkvetkin.scrapper.repository;

import io.github.pvkvetkin.scrapper.entity.Chat;
import io.micrometer.observation.annotation.Observed;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

import static io.github.pvkvetkin.scrapper.domain.jooq.Tables.CHAT;
import static io.github.pvkvetkin.scrapper.domain.jooq.Tables.LINK_CHAT;

@Repository
@AllArgsConstructor
@Observed
public class JooqChatRepository implements ChatRepository {

    private final DSLContext context;

    @Override
    public void add(Long id) {
        try {
            context.insertInto(CHAT)
                .set(CHAT.ID, id)
                .execute();
        } catch (org.jooq.exception.DataAccessException e) {
            throw new org.springframework.dao.DuplicateKeyException("Chat with id: " + id + " already registered.", e);
        }
    }

    @Override
    public void remove(Long id) {
        context.deleteFrom(LINK_CHAT)
            .where(LINK_CHAT.CHAT_ID.eq(id))
            .execute();
        if (context.deleteFrom(CHAT)
                .where(CHAT.ID.eq(id))
                .execute() == 0) {
            throw new EmptyResultDataAccessException("Chat with id " + id + " not registered.", 1);
        }
    }

    @Override
    public List<Chat> findAll() {
        return context
            .selectFrom(CHAT)
            .fetchInto(Chat.class);
    }
}
