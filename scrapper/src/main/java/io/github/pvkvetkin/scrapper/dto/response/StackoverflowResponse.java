package io.github.pvkvetkin.scrapper.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.pvkvetkin.scrapper.dto.LinkType;
import java.time.OffsetDateTime;
import java.util.Objects;

public record StackoverflowResponse(
    @JsonProperty("answer_count")
    Integer answerCount,
    @JsonProperty("creation_date")
    OffsetDateTime updatedAt
) implements ContentResponse {

    @Override
    public LinkType type() {
        return LinkType.STACKOVERFLOW;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StackoverflowResponse that = (StackoverflowResponse) o;

        OffsetDateTime thisUpdatedAt = this.updatedAt.withOffsetSameInstant(OffsetDateTime.now().getOffset());
        OffsetDateTime thatUpdatedAt = that.updatedAt.withOffsetSameInstant(OffsetDateTime.now().getOffset());
        return Objects.equals(answerCount, that.answerCount) && Objects.equals(thisUpdatedAt, thatUpdatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answerCount, updatedAt);
    }
}
