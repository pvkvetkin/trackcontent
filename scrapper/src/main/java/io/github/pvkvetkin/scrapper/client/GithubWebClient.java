package io.github.pvkvetkin.scrapper.client;

import io.github.pvkvetkin.scrapper.dto.response.GithubResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@AllArgsConstructor
public class GithubWebClient {

    private final WebClient client;
    private final String GITHUB_TOKEN;

    public GithubWebClient(String baseUrl, String token) {
        client = WebClient.create(baseUrl);
        GITHUB_TOKEN = token;
    }

    public GithubResponse fetchRepository(String username, String repository) {
        return client
            .get()
            .uri(uf -> uf
                .path("/repos/{username}/{repository}")
                .build(username, repository))
            .headers(httpHeaders -> httpHeaders.setBearerAuth(GITHUB_TOKEN))
            .retrieve()
            .bodyToMono(GithubResponse.class)
            .block();
    }
}
