package io.github.pvkvetkin.bot.dto.response;

import java.util.List;

public record ListLinksResponse(
        List<LinkResponse> items,
        Integer size
) {
}
