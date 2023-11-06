package io.github.pvkvetkin.scrapper.handler;

import io.github.pvkvetkin.scrapper.dto.response.ApiErrorResponse;
import java.util.Arrays;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(new ApiErrorResponse(
                "Wrong request parameters",
                e.getStatusCode().toString(),
                e.getTitleMessageCode(),
                e.getMessage(),
                Arrays.stream(e.getStackTrace()).map(Objects::toString).toList()
        ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(HttpClientErrorException.NotFound e)  {
        return new ResponseEntity<>(new ApiErrorResponse(
                "Requested resource not found",
                String.valueOf(404),
                e.getStatusCode().toString(),
                e.getMessage(),
                Arrays.stream(e.getStackTrace()).map(Objects::toString).toList()
        ), HttpStatus.NOT_FOUND);
    }
}
