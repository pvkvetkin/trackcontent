package io.github.pvkvetkin.scrapper.schedule;

import io.github.pvkvetkin.scrapper.dto.request.LinkUpdateRequest;
import io.github.pvkvetkin.scrapper.dto.response.ContentResponse;
import io.github.pvkvetkin.scrapper.entity.Link;
import io.github.pvkvetkin.scrapper.service.LinkService;
import io.github.pvkvetkin.scrapper.service.LinkUpdater;
import io.github.pvkvetkin.scrapper.service.parser.HttpContentLinkParser;
import io.github.pvkvetkin.scrapper.service.updatesender.UpdateSender;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@EnableScheduling
@RequiredArgsConstructor
public class LinkUpdaterScheduler implements LinkUpdater {

    private final UpdateSender updateSender;
    private final LinkService linkService;
    private final HttpContentLinkParser httpContentLinkParser;

    @Scheduled(fixedDelayString = "#{@schedulerIntervalMs}")
    public void update() {
        List<Link> notUpdatedLinks = linkService.getAllNotUpdatedLinks(1L);
        log.info("notUpdatedLinks = " + notUpdatedLinks);
        if (!notUpdatedLinks.isEmpty()) {
            for (Link link : notUpdatedLinks) {
                ContentResponse contentResponse = httpContentLinkParser.parseLink(link.getUrl().toString());
                LinkUpdateRequest request = linkService.updateLinkData(link.getId(), contentResponse);
                if (request != null) {
                    updateSender.sentUpdate(request);
                    log.info(request.toString());
                } else {
                    log.info("No changes for link: " + link.getUrl());
                }
            }
        }
    }
}
