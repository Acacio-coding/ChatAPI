package com.ifsc.chatapi.service;


public interface RabbitMQService {

    void createQueue(String queueName);

    void bindQueueToFanOut(String queueName);

    void deleteQueue(String queueName);

    <T> void sendToQueue(String queueName, T content);

    <T> void sendToExchange(T content);
}
