package io.github.pvkvetkin.scrapper.repository;

import io.github.pvkvetkin.scrapper.entity.Chat;
import io.github.pvkvetkin.scrapper.exception.TgChatIdNotFound;
import java.util.List;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import static io.github.pvkvetkin.scrapper.domain.jooq.Tables.CHAT;
import static io.github.pvkvetkin.scrapper.domain.jooq.Tables.LINK_CHAT;

@Repository
@AllArgsConstructor
public class JooqChatRepository implements ChatRepository {

    private final DSLContext context;

    @Override
    public void add(Long id) {
        context.insertInto(CHAT)
                .set(CHAT.ID, id)
                .execute();
    }

    @Override
    public void remove(Long id) {
        context.deleteFrom(LINK_CHAT)
                .where(LINK_CHAT.CHAT_ID.eq(id))
                .execute();
        if (context.deleteFrom(CHAT)
                .where(CHAT.ID.eq(id))
                .execute() == 0) {
            throw new TgChatIdNotFound("Chat with id " + id + " not registered.");
        }
    }

    @Override
    public List<Chat> findAll() {
        return context
                .selectFrom(CHAT)
                .fetchInto(Chat.class);
    }
}
