package io.github.pvkvetkin.linkparser.handler;

import io.github.pvkvetkin.linkparser.dto.GithubLinkDto;
import io.github.pvkvetkin.linkparser.dto.LinkDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
@AllArgsConstructor
public final class GithubHandler implements ContentHandler {

    private ContentHandler successor;
    private final String GITHUB_LINK = "https://github.com/([\\w-]+)/([\\w-]+)";
    private final Pattern gitHubPattern = Pattern.compile(GITHUB_LINK, Pattern.CASE_INSENSITIVE);

    @Override
    public LinkDto parse(
            @NotNull String url
    ) {
        Matcher matcher = gitHubPattern.matcher(url);

        if (matcher.find()) {
            return new GithubLinkDto(url.split("/")[3], url.split("/")[4]);
        } else if (successor != null) {
            return successor.parse(url);
        }

        return null;
    }
}
