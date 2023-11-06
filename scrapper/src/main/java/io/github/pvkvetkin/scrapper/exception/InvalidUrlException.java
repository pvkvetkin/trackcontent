package io.github.pvkvetkin.scrapper.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidUrlException extends RuntimeException {

    private final HttpStatus status = HttpStatus.CONFLICT;

    public InvalidUrlException(String message) {
        super(message);
    }

    public InvalidUrlException(String message, Throwable cause) {
        super(message, cause);
    }
}
