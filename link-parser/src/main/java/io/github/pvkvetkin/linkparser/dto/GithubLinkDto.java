package io.github.pvkvetkin.linkparser.dto;

public record GithubLinkDto(
        String username,
        String repository
) implements LinkDto {
}
