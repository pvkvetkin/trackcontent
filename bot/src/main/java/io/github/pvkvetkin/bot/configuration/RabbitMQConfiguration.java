package io.github.pvkvetkin.bot.configuration;

import io.github.pvkvetkin.bot.dto.request.LinkUpdateRequest;
import java.util.HashMap;
import java.util.Map;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.ClassMapper;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    String exchangeName;
    String queueName;

    public static final String POSTFIX_DLQ = ".dlq";

    public RabbitMQConfiguration(ApplicationConfig applicationConfig) {
        this.exchangeName = applicationConfig.rabbitmq().exchangeName();
        this.queueName = applicationConfig.rabbitmq().queueName();
    }

    @Bean
    Queue messagesQueue() {
        return QueueBuilder.durable(queueName)
            .withArgument("x-dead-letter-exchange", "")
            .withArgument("x-dead-letter-routing-key", queueName + POSTFIX_DLQ)
            .build();
    }

    @Bean
    Queue deadLetterQueue() {
        return QueueBuilder.durable(queueName + POSTFIX_DLQ).build();
    }

    @Bean
    DirectExchange messagesExchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    Binding bindingMessages() {
        return BindingBuilder.bind(messagesQueue()).to(messagesExchange()).with(queueName);
    }

    @Bean
    public ClassMapper classMapper() {
        Map<String, Class<?>> mappings = new HashMap<>();
        mappings.put("io.github.pvkvetkin.scrapper.dto.request.LinkUpdateRequest", LinkUpdateRequest.class);

        DefaultClassMapper classMapper = new DefaultClassMapper();
        classMapper.setTrustedPackages("io.github.pvkvetkin.scrapper.dto.*");
        classMapper.setIdClassMapping(mappings);
        return classMapper;
    }

    @Bean
    public MessageConverter jsonMessageConverter(ClassMapper classMapper) {
        Jackson2JsonMessageConverter jsonConverter = new Jackson2JsonMessageConverter();
        jsonConverter.setClassMapper(classMapper);
        return jsonConverter;
    }
}
