package io.github.pvkvetkin.bot.dto.request;

import java.net.URI;
import org.jetbrains.annotations.NotNull;

public record AddLinksRequest(
        @NotNull
        URI link
) {
}

