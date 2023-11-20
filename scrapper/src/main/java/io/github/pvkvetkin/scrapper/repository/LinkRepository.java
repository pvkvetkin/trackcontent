package io.github.pvkvetkin.scrapper.repository;

import io.github.pvkvetkin.scrapper.dto.response.GithubResponse;
import io.github.pvkvetkin.scrapper.dto.response.StackoverflowResponse;
import io.github.pvkvetkin.scrapper.entity.Link;
import io.github.pvkvetkin.scrapper.entity.LinkType;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface LinkRepository {

    Long add(URI uri, Long chatId, LinkType type);

    void remove(URI uri, Long chatId);

    List<Link> findAll(Long chatId);

    Optional<Link> findByUrl(URI uri);

    Optional<Link> findById(Long linkId);

    List<Link> checkUncheckedLinks(Long delayMinutes);

    void insertGithubData(Long linkId, OffsetDateTime lastCommitAt, Integer issuesCount, OffsetDateTime updatedAt);

    void insertStackoverflowData(Long linkId, Integer answerCount, OffsetDateTime updatedAt);

    void updateGithubData(Long linkId, OffsetDateTime lastCommitAt, Integer issuesCount, OffsetDateTime updatedAt);

    void updateStackoverflowData(Long linkId, Integer answerCount, OffsetDateTime updatedAt);

    List<Long> findChatIdsByLinkIds(Long linkId);

    GithubResponse findGithubLinkById(Long linkId);

    StackoverflowResponse findStackoverflowLinkById(Long linkId);
}
