package io.github.pvkvetkin.scrapper.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record StackOverflowItemsResponse(
    @JsonProperty("items")
    List<StackoverflowResponse> items
) {
}
