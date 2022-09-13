package com.ifsc.chatapi.service;

import com.ifsc.chatapi.advice.exception.ValidationException;
import com.ifsc.chatapi.dto.MessageToGroupDTO;
import com.ifsc.chatapi.dto.MessageToUserDTO;
import com.ifsc.chatapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import static com.ifsc.chatapi.utils.constants.EndPoints.*;
import static com.ifsc.chatapi.utils.constants.MQConstants.GROUP_EXCHANGE_BASE;
import static com.ifsc.chatapi.utils.constants.MQConstants.USER_QUEUE_BASE;
import static com.ifsc.chatapi.utils.constants.ValidationMessages.RECEIVER_NOT_FOUND;
import static com.ifsc.chatapi.utils.constants.ValidationMessages.SENDER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class MessageServiceImplementation implements MessageService {

    private final UserRepository userRepository;
    private final RabbitMQServiceImplementation rabbitMQService;


    @Override
    public void sendToUser(MessageToUserDTO message) {
        if (!this.userRepository.existsByUsername(message.getSender())) {
            throw new ValidationException(
                    HttpStatus.NOT_FOUND,
                    SENDER_NOT_FOUND,
                    BASE_ENDPOINT + MESSAGE_BASE_ENDPOINT + MESSAGE_SEND_TO_USER_ENDPOINT);
        }

        if (!this.userRepository.existsByUsername(message.getReceiver())) {
            throw new ValidationException(
                    HttpStatus.NOT_FOUND,
                    RECEIVER_NOT_FOUND,
                    BASE_ENDPOINT + MESSAGE_BASE_ENDPOINT + MESSAGE_SEND_TO_USER_ENDPOINT);
        }

        var receiver = this.userRepository.findByUsername(message.getReceiver());

        String queueName = USER_QUEUE_BASE + receiver.getId();
        this.rabbitMQService.sendToQueue(queueName, message);
    }

    @Override
    public void sendToGroup(MessageToGroupDTO message) {
        var sender = this.userRepository.findByUsername(message.getSender());

        if (sender == null) {
            throw new ValidationException(
                    HttpStatus.NOT_FOUND,
                    SENDER_NOT_FOUND,
                    BASE_ENDPOINT + MESSAGE_BASE_ENDPOINT + MESSAGE_SEND_TO_GROUP_ENDPOINT);
        }

        this.rabbitMQService.sendToExchange(message);
    }
}
