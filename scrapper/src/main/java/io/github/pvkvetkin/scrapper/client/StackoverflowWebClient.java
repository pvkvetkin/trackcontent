package io.github.pvkvetkin.scrapper.client;

import io.github.pvkvetkin.scrapper.dto.response.StackOverflowItemsResponse;
import io.github.pvkvetkin.scrapper.dto.response.StackoverflowResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@AllArgsConstructor
public class StackoverflowWebClient {

    private final WebClient client;

    public StackoverflowWebClient(String baseUrl) {
        client = WebClient.create(baseUrl);
    }

    public StackoverflowResponse fetchRepository(String id) {
        return Objects.requireNonNull(client
                .get()
                .uri(uf -> uf
                    .path("/questions/{id}")
                    .queryParam("order", "desc")
                    .queryParam("sort", "activity")
                    .queryParam("site", "stackoverflow")
                    .build(id)
                )
                .acceptCharset(StandardCharsets.UTF_8)
                .retrieve()
                .bodyToMono(StackOverflowItemsResponse.class)
                .block())
            .items().get(0);
    }
}
