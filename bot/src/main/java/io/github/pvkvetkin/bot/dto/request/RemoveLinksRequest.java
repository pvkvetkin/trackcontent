package io.github.pvkvetkin.bot.dto.request;

import org.jetbrains.annotations.NotNull;

import java.net.URI;

public record RemoveLinksRequest(
        @NotNull
        URI link
) {
}
