package io.github.pvkvetkin.linkparser.dto;

sealed public interface LinkDto permits GithubLinkDto, StackOverFlowLinkDto {
}
