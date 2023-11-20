package io.github.pvkvetkin.scrapper.controller;

import io.github.pvkvetkin.scrapper.dto.response.ApiErrorResponse;
import io.github.pvkvetkin.scrapper.exception.DuplicateChatIdException;
import io.github.pvkvetkin.scrapper.exception.TgChatIdNotFound;
import io.github.pvkvetkin.scrapper.service.ChatService;
import io.micrometer.observation.annotation.Observed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Min;
import java.util.Arrays;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ApiResponse(responseCode = "400", description = "Invalid request parameters", content = {
        @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ApiErrorResponse.class))
})
@RestController
@AllArgsConstructor
@RequestMapping("/tg-chat/{id}")
@Observed
public class ChatController {

    private final ChatService chatService;

    @Operation(summary = "Register a chat")
    @ApiResponse(responseCode = "200", description = "Chat registered")
    @PostMapping
    public ResponseEntity<Void> registerChat(@PathVariable @Min(0) Long id) {
        chatService.register(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Delete a chat")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Chat successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Chat does not exist", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))
            })
    })
    @DeleteMapping
    public ResponseEntity<Void> deleteChat(@PathVariable @Min(0) Long id) {
        chatService.unregister(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ExceptionHandler(DuplicateChatIdException.class)
    public ResponseEntity<ApiErrorResponse> handleDuplicateKeyException(DuplicateChatIdException e) {
        return new ResponseEntity<>(new ApiErrorResponse(
                "Wrong request with duplicate chat parameter.",
                e.getStatus().toString(),
                e.getStatus().name(),
                e.getMessage(),
                Arrays.stream(e.getStackTrace()).map(Objects::toString).toList()
        ), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(TgChatIdNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleNotFoundChatException(TgChatIdNotFound e) {
        return new ResponseEntity<>(new ApiErrorResponse(
                "Wrong request with non-existent chat parameter.",
                e.getStatus().toString(),
                e.getStatus().name(),
                e.getMessage(),
                Arrays.stream(e.getStackTrace()).map(Objects::toString).toList()
        ), HttpStatus.NOT_FOUND);
    }
}
