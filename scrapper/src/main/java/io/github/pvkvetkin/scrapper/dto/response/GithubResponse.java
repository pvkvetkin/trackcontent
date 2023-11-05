package io.github.pvkvetkin.scrapper.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.pvkvetkin.scrapper.dto.LinkType;
import java.time.OffsetDateTime;
import java.util.Objects;

public record GithubResponse(
    @JsonProperty("pushed_at")
    OffsetDateTime lastCommitAt,
    @JsonProperty("open_issues_count")
    Integer issuesCount,
    @JsonProperty("updated_at")
    OffsetDateTime updatedAt
) implements ContentResponse {

    @Override
    public LinkType type() {
        return LinkType.GITHUB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GithubResponse that = (GithubResponse) o;
        return Objects.equals(lastCommitAt, that.lastCommitAt)
               && Objects.equals(issuesCount, that.issuesCount)
               && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastCommitAt, issuesCount, updatedAt);
    }
}
