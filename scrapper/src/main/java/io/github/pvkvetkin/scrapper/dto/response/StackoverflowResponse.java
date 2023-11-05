package io.github.pvkvetkin.scrapper.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.pvkvetkin.scrapper.dto.LinkType;
import java.time.OffsetDateTime;

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
}
