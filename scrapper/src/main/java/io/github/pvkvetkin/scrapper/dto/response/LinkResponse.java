package io.github.pvkvetkin.scrapper.dto.response;

import java.net.URI;

public record LinkResponse(
        Long id,
        URI url
) {
}
