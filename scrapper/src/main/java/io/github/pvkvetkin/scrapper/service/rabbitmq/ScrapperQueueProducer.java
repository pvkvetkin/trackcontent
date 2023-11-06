package io.github.pvkvetkin.scrapper.service.rabbitmq;

import io.github.pvkvetkin.scrapper.dto.request.LinkUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@RequiredArgsConstructor
public class ScrapperQueueProducer {

    private final String queueName;
    private final RabbitTemplate template;

    public void send(LinkUpdateRequest update) {
        template.convertAndSend(queueName, update);
    }
}
