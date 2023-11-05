package io.github.pvkvetkin.scrapper.service;

import io.github.pvkvetkin.scrapper.dto.request.LinkUpdateRequest;
import io.github.pvkvetkin.scrapper.dto.response.ContentResponse;
import io.github.pvkvetkin.scrapper.dto.response.LinkResponse;
import io.github.pvkvetkin.scrapper.entity.Link;
import java.net.URI;
import java.util.List;

public interface LinkService {

    LinkResponse add(Long tgChatId, URI uri);
    LinkResponse remove(Long tgChatId, URI uri);
    List<Link> getAllTrackedLinks(Long tgChatId);

    List<Link> getAllNotUpdatedLinks(Long delayMinutes);

    LinkUpdateRequest updateLinkData(Long linkId, ContentResponse contentResponse);
}
