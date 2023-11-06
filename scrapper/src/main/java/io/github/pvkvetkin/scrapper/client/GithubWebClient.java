package io.github.pvkvetkin.scrapper.client;

import io.github.pvkvetkin.scrapper.dto.response.GithubResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@AllArgsConstructor
public class GithubWebClient {

    private final WebClient client;

    public GithubWebClient(String baseUrl) {
        client = WebClient.create(baseUrl);
    }

    public GithubResponse fetchRepository(String username, String repository) {
        return client
                .get()
                .uri(uf -> uf
                        .path("/repos/{username}/{repository}")
                        .build(username, repository))
                .retrieve()
                .bodyToMono(GithubResponse.class)
                .block();
    }
}
