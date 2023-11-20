package io.github.pvkvetkin.scrapper.repository;

import io.github.pvkvetkin.scrapper.dto.response.GithubResponse;
import io.github.pvkvetkin.scrapper.dto.response.StackoverflowResponse;
import io.github.pvkvetkin.scrapper.entity.Link;
import io.github.pvkvetkin.scrapper.entity.LinkType;
import io.micrometer.observation.annotation.Observed;
import java.net.URI;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import static io.github.pvkvetkin.scrapper.domain.jooq.Tables.GITHUB_LINK;
import static io.github.pvkvetkin.scrapper.domain.jooq.Tables.LINK;
import static io.github.pvkvetkin.scrapper.domain.jooq.Tables.LINK_CHAT;
import static io.github.pvkvetkin.scrapper.domain.jooq.Tables.STACKOVERFLOW_LINK;

@Repository
@AllArgsConstructor
@Observed
public class JooqLinkRepository implements LinkRepository {

    private final DSLContext context;

    @Override
    public Long add(URI uri, Long chatId, LinkType type) {
        Long linkId = context.insertInto(LINK)
            .set(LINK.URL, uri.toString())
            .set(LINK.LAST_CHECK_AT, OffsetDateTime.now().withOffsetSameInstant(ZoneOffset.UTC))
            .set(LINK.UPDATED_AT, OffsetDateTime.now().withOffsetSameInstant(ZoneOffset.UTC))
            .set(LINK.LINK_TYPE, type.name())
            .returning(LINK.ID)
            .fetchOne(LINK.ID);

        context.insertInto(LINK_CHAT)
            .set(LINK_CHAT.LINK_ID, linkId)
            .set(LINK_CHAT.CHAT_ID, chatId)
            .execute();

        return linkId;
    }

    @Override
    public void remove(URI uri, Long chatId) {
        Long linkId = context.deleteFrom(LINK_CHAT)
            .where(LINK_CHAT.CHAT_ID.eq(chatId))
            .and(LINK_CHAT.LINK_ID.eq(context.selectFrom(LINK)
                .where(LINK.URL.eq(uri.toString()))
                .fetchOne(LINK.ID))
            )
            .returning(LINK_CHAT.LINK_ID)
            .fetchOne(LINK_CHAT.LINK_ID);

        Integer count = context.selectCount()
            .from(LINK_CHAT)
            .join(LINK).on(LINK_CHAT.LINK_ID.eq(LINK.ID))
            .where(LINK.URL.eq(uri.toString()))
            .fetchOne(0, Integer.class);

        if (count == null || count.equals(0)) {
            context.deleteFrom(LINK)
                .where(LINK.URL.eq(uri.toString()))
                .execute();
        }
    }

    @Override
    public List<Link> findAll(Long chatId) {
        return context.selectFrom(LINK)
            .where(LINK.ID.in(context.select(LINK_CHAT.LINK_ID)
                .from(LINK_CHAT)
                .where(LINK_CHAT.CHAT_ID.eq(chatId))))
            .fetchInto(Link.class);
    }

    @Override
    public Optional<Link> findByUrl(URI uri) {
        return context.selectFrom(LINK)
            .where(LINK.URL.eq(uri.toString()))
            .fetchOptionalInto(Link.class);
    }

    @Override
    public Optional<Link> findById(Long linkId) {
        return context.selectFrom(LINK)
            .where(LINK.ID.eq(linkId))
            .fetchOptionalInto(Link.class);
    }

    @Override
    public List<Link> checkUncheckedLinks(Long delayMinutes) {
        return context.select(LINK.ID, LINK.URL, LINK.LAST_CHECK_AT, LINK.UPDATED_AT)
            .from(LINK)
            .join(LINK_CHAT).on(LINK_CHAT.LINK_ID.eq(LINK.ID))
            .where(LINK.LAST_CHECK_AT.lessThan(
                OffsetDateTime.now().withOffsetSameInstant(ZoneOffset.UTC).minusMinutes(delayMinutes))
            )
            .fetchInto(Link.class);
    }

    @Override
    public void insertGithubData(Long linkId, OffsetDateTime lastCommitAt, Integer issuesCount, OffsetDateTime updatedAt) {
        context.insertInto(GITHUB_LINK)
            .set(GITHUB_LINK.LINK_ID, linkId)
            .set(GITHUB_LINK.LAST_COMMIT_AT, lastCommitAt.withOffsetSameInstant(ZoneOffset.UTC))
            .set(GITHUB_LINK.ISSUES_COUNT, issuesCount)
            .set(GITHUB_LINK.UPDATED_AT, updatedAt.withOffsetSameInstant(ZoneOffset.UTC))
            .execute();
    }

    @Override
    public void insertStackoverflowData(Long linkId, Integer answerCount, OffsetDateTime updatedAt) {
        context.insertInto(STACKOVERFLOW_LINK)
            .set(STACKOVERFLOW_LINK.LINK_ID, linkId)
            .set(STACKOVERFLOW_LINK.ANSWER_COUNT, answerCount)
            .set(STACKOVERFLOW_LINK.UPDATED_AT, updatedAt.withOffsetSameInstant(ZoneOffset.UTC))
            .execute();
    }

    @Override
    public void updateGithubData(Long linkId, OffsetDateTime lastCommitAt, Integer issuesCount, OffsetDateTime updatedAt) {
        context.update(GITHUB_LINK)
            .set(GITHUB_LINK.LAST_COMMIT_AT, lastCommitAt.withOffsetSameInstant(ZoneOffset.UTC))
            .set(GITHUB_LINK.ISSUES_COUNT, issuesCount)
            .set(GITHUB_LINK.UPDATED_AT, updatedAt.withOffsetSameInstant(ZoneOffset.UTC))
            .where(GITHUB_LINK.LINK_ID.eq(linkId))
            .execute();
    }

    @Override
    public void updateStackoverflowData(Long linkId, Integer answerCount, OffsetDateTime updatedAt) {
        context.update(STACKOVERFLOW_LINK)
            .set(STACKOVERFLOW_LINK.ANSWER_COUNT, answerCount)
            .set(STACKOVERFLOW_LINK.UPDATED_AT, updatedAt.withOffsetSameInstant(ZoneOffset.UTC))
            .where(STACKOVERFLOW_LINK.LINK_ID.eq(linkId))
            .execute();
    }

    @Override
    public List<Long> findChatIdsByLinkIds(Long linkId) {
        return context.select(LINK_CHAT.CHAT_ID)
            .from(LINK_CHAT)
            .where(LINK_CHAT.LINK_ID.eq(linkId))
            .fetchInto(Long.class);
    }

    @Override
    public GithubResponse findGithubLinkById(Long linkId) {
        return context.select(GITHUB_LINK.LAST_COMMIT_AT, GITHUB_LINK.ISSUES_COUNT, GITHUB_LINK.UPDATED_AT)
            .from(GITHUB_LINK)
            .where(GITHUB_LINK.LINK_ID.eq(linkId))
            .fetchOneInto(GithubResponse.class);
    }

    @Override
    public StackoverflowResponse findStackoverflowLinkById(Long linkId) {
        return context.select(STACKOVERFLOW_LINK.ANSWER_COUNT, STACKOVERFLOW_LINK.UPDATED_AT)
            .from(STACKOVERFLOW_LINK)
            .where(STACKOVERFLOW_LINK.LINK_ID.eq(linkId))
            .fetchOneInto(StackoverflowResponse.class);
    }
}
