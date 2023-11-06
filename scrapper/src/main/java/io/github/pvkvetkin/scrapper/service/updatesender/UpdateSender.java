package io.github.pvkvetkin.scrapper.service.updatesender;

import io.github.pvkvetkin.scrapper.dto.request.LinkUpdateRequest;

public interface UpdateSender {

    void sentUpdate(LinkUpdateRequest update);
}
