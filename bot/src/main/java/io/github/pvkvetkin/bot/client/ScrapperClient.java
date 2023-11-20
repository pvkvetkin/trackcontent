package io.github.pvkvetkin.bot.client;

import io.github.pvkvetkin.bot.dto.request.AddLinksRequest;
import io.github.pvkvetkin.bot.dto.request.RemoveLinksRequest;
import io.github.pvkvetkin.bot.dto.response.LinkResponse;
import io.github.pvkvetkin.bot.dto.response.ListLinksResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

@AllArgsConstructor
public class ScrapperClient {

    private final WebClient client;

    private final static String TG_CHAT_PATH = "/tg-chat/{id}";
    private final static String TG_CHAT_HEADER = "Tg-Chat-Id";
    private final static String LINKS_PATH = "/links";

    public void registerChat(Long id) {
        client
            .post()
            .uri(uf -> uf
                .path(TG_CHAT_PATH)
                .build(id))
            .retrieve()
            .bodyToMono(Void.class)
            .block();
    }

    public void deleteChat(Long id) {
        client
            .delete()
            .uri(uf -> uf
                .path(TG_CHAT_PATH)
                .build(id))
            .retrieve()
            .bodyToMono(Void.class)
            .block();
    }

    public ListLinksResponse getAllTrackedLinks(Long id) {
        return client
            .get()
            .uri(LINKS_PATH)
            .header(TG_CHAT_HEADER, String.valueOf(id))
            .retrieve()
            .bodyToMono(ListLinksResponse.class)
            .block();
    }

    public void addLinkTracking(Long id, AddLinksRequest response) {
        client
            .post()
            .uri(LINKS_PATH)
            .header(TG_CHAT_HEADER, String.valueOf(id))
            .bodyValue(response)
            .retrieve()
            .bodyToMono(LinkResponse.class)
            .block();
    }

    public void removeLinkTracking(Long id, RemoveLinksRequest response) {
        client
            .method(HttpMethod.DELETE)
            .uri(LINKS_PATH)
            .header(TG_CHAT_HEADER, String.valueOf(id))
            .bodyValue(response)
            .retrieve()
            .bodyToMono(LinkResponse.class)
            .block();
    }
}
