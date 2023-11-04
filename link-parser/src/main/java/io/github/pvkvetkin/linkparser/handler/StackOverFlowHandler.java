package io.github.pvkvetkin.linkparser.handler;

import io.github.pvkvetkin.linkparser.dto.LinkDto;
import io.github.pvkvetkin.linkparser.dto.StackOverFlowLinkDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
@AllArgsConstructor
public final class StackOverFlowHandler implements ContentHandler {

    private ContentHandler successor;
    private final String STACK_OVER_FLOW_LINK = "https://stackoverflow.com/questions/(\\d+)/";
    private final Pattern stackOverflowPattern = Pattern.compile(STACK_OVER_FLOW_LINK, Pattern.CASE_INSENSITIVE);

    @Override
    public LinkDto parse(
            @NotNull String url
    ) {
        Matcher matcher = stackOverflowPattern.matcher(url);

        if (matcher.find()) {
            return new StackOverFlowLinkDto(url.split("/")[4]);
        } else if (successor != null) {
            return successor.parse(url);
        }

        return null;
    }
}
