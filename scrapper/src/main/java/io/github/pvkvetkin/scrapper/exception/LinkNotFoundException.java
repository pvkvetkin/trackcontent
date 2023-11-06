package io.github.pvkvetkin.scrapper.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class LinkNotFoundException extends RuntimeException {

    private final HttpStatus status = HttpStatus.NOT_FOUND;

    public LinkNotFoundException(String message) {
        super(message);
    }

    public LinkNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
