package com.ifsc.chatapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static com.ifsc.chatapi.utils.constants.MQConstants.*;

@Service
@RequiredArgsConstructor
public class RabbitMQServiceImplementation implements RabbitMQService {

    private final AmqpAdmin amqpAdmin;
    private final RabbitTemplate template;

    private Queue newQueue(String queueName) {
        return new Queue(queueName, true, false, false);
    }

    private DirectExchange newDirectExchange() {
        return new DirectExchange(DIRECT_EXCHANGE_NAME);
    }

    private FanoutExchange newFanOutExchange() {
        return new FanoutExchange(FAN_OUT_EXCHANGE_NAME);
    }

    private Binding newBindingToQueue(String queueName, String exchangeName) {
        return new Binding(queueName, Binding.DestinationType.QUEUE, exchangeName, queueName, null);
    }

    private Binding newBindingToFanOutExchange(Queue queue, FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Override
    public void createQueue(String queueName) {
        var queue = this.newQueue(queueName);
        var exchange = this.newDirectExchange();
        var binding = this.newBindingToQueue(queueName, exchange.getName());

        this.amqpAdmin.declareQueue(queue);
        this.amqpAdmin.declareExchange(exchange);
        this.amqpAdmin.declareBinding(binding);
    }

    @Override
    public void bindQueueToFanOut(String queueName) {
        var queue = this.newQueue(queueName);
        var exchange = this.newFanOutExchange();
        var binding = this.newBindingToFanOutExchange(queue, exchange);

        this.amqpAdmin.declareQueue(queue);
        this.amqpAdmin.declareExchange(exchange);
        this.amqpAdmin.declareBinding(binding);
    }

    @Override
    public void deleteQueue(String queueName) {
        this.amqpAdmin.deleteQueue(queueName);
    }

    @Override
    public <T> void sendToQueue(String queueName, T content) {
        this.template.convertAndSend(queueName, content);
    }

    @Override
    public <T> void sendToExchange(T content) {
        this.template.convertAndSend(FAN_OUT_EXCHANGE_NAME, content);
    }
}
