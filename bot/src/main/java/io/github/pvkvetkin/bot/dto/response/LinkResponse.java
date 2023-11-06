package io.github.pvkvetkin.bot.dto.response;

import java.net.URI;

public record LinkResponse(
        Long id,
        URI url
) {
}
