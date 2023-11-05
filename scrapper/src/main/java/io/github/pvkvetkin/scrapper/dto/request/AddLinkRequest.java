package io.github.pvkvetkin.scrapper.dto.request;

import java.net.URI;
import org.jetbrains.annotations.NotNull;

public record AddLinkRequest(
        @NotNull
        URI link
) {
}
