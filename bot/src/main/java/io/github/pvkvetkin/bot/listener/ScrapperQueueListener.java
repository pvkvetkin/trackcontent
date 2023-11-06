package io.github.pvkvetkin.bot.listener;

import io.github.pvkvetkin.bot.dto.request.LinkUpdateRequest;
import io.github.pvkvetkin.bot.service.UpdateService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RabbitListener(queues = "${tg-bot.rabbitmq.queue-name}")
@Service
@AllArgsConstructor
public class ScrapperQueueListener {

    private final UpdateService service;

    @RabbitHandler
    public void receiver(LinkUpdateRequest update) {
        service.handleUpdate(
            update.id(),
            update.url(),
            update.description(),
            update.tgChatIds()
        );
    }
}
