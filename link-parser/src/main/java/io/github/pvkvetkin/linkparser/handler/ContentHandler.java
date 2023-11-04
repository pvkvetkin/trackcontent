package io.github.pvkvetkin.linkparser.handler;

import io.github.pvkvetkin.linkparser.dto.LinkDto;
import jakarta.validation.constraints.NotNull;

public sealed interface ContentHandler permits GithubHandler, StackOverFlowHandler {

    LinkDto parse(@NotNull String url);
}
