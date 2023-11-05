package io.github.pvkvetkin.scrapper.dto.response;

import io.github.pvkvetkin.scrapper.dto.LinkType;

public sealed interface ContentResponse permits GithubResponse, StackoverflowResponse {

    LinkType type();
}
