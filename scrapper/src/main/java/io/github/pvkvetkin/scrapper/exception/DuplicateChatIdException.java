package io.github.pvkvetkin.scrapper.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DuplicateChatIdException extends RuntimeException {

    private final HttpStatus status = HttpStatus.CONFLICT;

    public DuplicateChatIdException(String message) {
        super(message);
    }

    public DuplicateChatIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
