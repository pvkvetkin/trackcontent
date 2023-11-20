package io.github.pvkvetkin.scrapper.controller;

import io.github.pvkvetkin.scrapper.dto.request.AddLinkRequest;
import io.github.pvkvetkin.scrapper.dto.request.RemoveLinkRequest;
import io.github.pvkvetkin.scrapper.dto.response.ApiErrorResponse;
import io.github.pvkvetkin.scrapper.dto.response.LinkResponse;
import io.github.pvkvetkin.scrapper.dto.response.ListLinksResponse;
import io.github.pvkvetkin.scrapper.entity.Link;
import io.github.pvkvetkin.scrapper.exception.InvalidUrlException;
import io.github.pvkvetkin.scrapper.exception.LinkNotFoundException;
import io.github.pvkvetkin.scrapper.service.LinkService;
import io.micrometer.observation.annotation.Observed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ApiResponse(responseCode = "400", description = "Invalid request parameters", content = {
    @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = ApiErrorResponse.class))
})
@RestController
@AllArgsConstructor
@RequestMapping("/links")
@Observed
public class LinkController {

    private final LinkService linkService;

    @Operation(summary = "Add link tracking")
    @ApiResponse(responseCode = "200", description = "Link successfully added", content = {
        @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = LinkResponse.class))
    })
    @PostMapping
    public ResponseEntity<LinkResponse> addLinkTracking(
        @RequestHeader(name = "Tg-Chat-Id") Long id,
        @Valid @RequestBody AddLinkRequest addLinkRequest
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(linkService.add(id, addLinkRequest.link()));
    }

    @Operation(summary = "Remove link tracking")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Link successfully removed", content = {
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = LinkResponse.class))
        }),
        @ApiResponse(responseCode = "404", description = "Link not found", content = {
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ApiErrorResponse.class))
        })
    })
    @DeleteMapping
    public ResponseEntity<LinkResponse> removeLinkTracking(
        @RequestHeader(name = "Tg-Chat-Id") Long id,
        @Valid @RequestBody RemoveLinkRequest removeLinkRequest
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(linkService.remove(id, removeLinkRequest.link()));
    }

    @Operation(summary = "Get all tracked links")
    @ApiResponse(responseCode = "200", description = "Links successfully retrieved", content = {
        @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ListLinksResponse.class))
    })
    @GetMapping
    public ResponseEntity<ListLinksResponse> getAllTrackedLinks(@RequestHeader(name = "Tg-Chat-Id") Long id) {
        List<Link> links = linkService.getAllTrackedLinks(id);
        return ResponseEntity.status(HttpStatus.OK).body(
            new ListLinksResponse(
                links.stream()
                    .map(link -> new LinkResponse(link.getId(), link.getUrl()))
                    .collect(Collectors.toList()),
                links.size()
            ));
    }

    @ExceptionHandler(InvalidUrlException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidUrlException(InvalidUrlException e) {
        return new ResponseEntity<>(new ApiErrorResponse(
            "Wrong request with invalid url parameter.",
            e.getStatus().toString(),
            e.getStatus().name(),
            e.getMessage(),
            Arrays.stream(e.getStackTrace()).map(Objects::toString).toList()
        ), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(LinkNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleLinkNotFoundException(LinkNotFoundException e) {
        return new ResponseEntity<>(new ApiErrorResponse(
            "Wrong request with non-existent link parameter.",
            e.getStatus().toString(),
            e.getStatus().name(),
            e.getMessage(),
            Arrays.stream(e.getStackTrace()).map(Objects::toString).toList()
        ), HttpStatus.NOT_FOUND);
    }
}
