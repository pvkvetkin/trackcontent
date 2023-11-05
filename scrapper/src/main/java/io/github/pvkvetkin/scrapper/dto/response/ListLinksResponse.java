package io.github.pvkvetkin.scrapper.dto.response;

import java.util.List;

public record ListLinksResponse(
        List<LinkResponse> items,
        Integer size
) {
}
