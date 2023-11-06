package io.github.pvkvetkin.scrapper.service.updatesender;

import io.github.pvkvetkin.scrapper.dto.request.LinkUpdateRequest;
import io.github.pvkvetkin.scrapper.service.rabbitmq.ScrapperQueueProducer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RabbitUpdateSender implements UpdateSender {

    private final ScrapperQueueProducer queueProducer;

    @Override
    public void sentUpdate(LinkUpdateRequest update) {
        queueProducer.send(update);
    }
}
