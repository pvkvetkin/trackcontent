package io.github.pvkvetkin.scrapper.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TgChatIdNotFound extends RuntimeException {

    private final HttpStatus status = HttpStatus.NOT_FOUND;

    public TgChatIdNotFound(String message) {
        super(message);
    }

    public TgChatIdNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
