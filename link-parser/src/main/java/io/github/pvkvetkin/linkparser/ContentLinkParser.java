package io.github.pvkvetkin.linkparser;

import io.github.pvkvetkin.linkparser.dto.LinkDto;
import io.github.pvkvetkin.linkparser.handler.ContentHandler;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
public class ContentLinkParser {

    private final ContentHandler handler;

    public LinkDto parseLink(@NotNull String url) {
        LinkDto linkDto = handler.parse(url);
        if (linkDto == null) {
            throw new IllegalArgumentException("Invalid URL.");
        }
        return linkDto;
    }
}
