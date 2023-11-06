package io.github.pvkvetkin.scrapper.configuration;

import io.github.pvkvetkin.scrapper.service.rabbitmq.ScrapperQueueProducer;
import io.github.pvkvetkin.scrapper.service.updatesender.RabbitUpdateSender;
import io.github.pvkvetkin.scrapper.service.updatesender.UpdateSender;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqSenderAccessConfig {

    @Bean
    ScrapperQueueProducer scrapperQueueProducer(ApplicationConfig config, RabbitTemplate template) {
        return new ScrapperQueueProducer(config.rabbitmq().queueName(), template);
    }

    @Bean
    UpdateSender updateSender(ScrapperQueueProducer queueProducer) {
        return new RabbitUpdateSender(queueProducer);
    }
}
