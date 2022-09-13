package com.ifsc.chatapi.service;

import com.ifsc.chatapi.advice.exception.ValidationException;
import com.ifsc.chatapi.dto.UserDTO;
import com.ifsc.chatapi.model.UserModel;
import com.ifsc.chatapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import static com.ifsc.chatapi.utils.constants.EndPoints.*;
import static com.ifsc.chatapi.utils.constants.MQConstants.USER_QUEUE_BASE;
import static com.ifsc.chatapi.utils.constants.ValidationMessages.*;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final RabbitMQService rabbitMQService;

    @Override
    public UserModel get(String username) {
        var user = this.userRepository.findByUsername(username);

        if (user == null) {
            throw new ValidationException(
                    HttpStatus.NOT_FOUND,
                    USER_NOT_FOUND,
                    BASE_ENDPOINT + USER_BASE_ENDPOINT + USER_GET_ENDPOINT);
        }

        return user;
    }

    @Override
    public UserModel create(UserDTO userDTO) {
        if (this.userRepository.existsByUsername(userDTO.getUsername())) {
            throw new ValidationException(
                    HttpStatus.CONFLICT,
                    USERNAME_ALREADY_TAKEN,
                    BASE_ENDPOINT + USER_BASE_ENDPOINT + USER_CREATION_ENDPOINT);
        }

        var newUser = new UserModel();
        BeanUtils.copyProperties(userDTO, newUser);

        this.userRepository.save(newUser);

        String queueName = USER_QUEUE_BASE + newUser.getId();
        this.rabbitMQService.createQueue(queueName);

        return newUser;
    }

    @Override
    public UserModel update(String username, UserDTO userDTO) {
        var user = this.userRepository.findByUsername(username);

        if (user == null) {
            throw new ValidationException(
                    HttpStatus.NOT_FOUND,
                    USER_NOT_FOUND,
                    BASE_ENDPOINT + USER_BASE_ENDPOINT + USER_UPDATE_ENDPOINT);
        }

        if (this.userRepository.existsByUsername(userDTO.getUsername())) {
            throw new ValidationException(
                    HttpStatus.CONFLICT,
                    USERNAME_ALREADY_TAKEN,
                    BASE_ENDPOINT + USER_BASE_ENDPOINT + USER_CREATION_ENDPOINT);
        }

        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());

        this.userRepository.save(user);

        return user;
    }

    @Override
    public UserModel delete(String username) {
        var user = this.userRepository.findByUsername(username);

        if (user == null) {
            throw new ValidationException(
                    HttpStatus.NOT_FOUND,
                    USER_NOT_FOUND,
                    BASE_ENDPOINT + USER_BASE_ENDPOINT + USER_DELETE_ENDPOINT);
        }

        this.userRepository.delete(user);

        String queueName = USER_QUEUE_BASE + user.getId();
        this.rabbitMQService.deleteQueue(queueName);

        return user;
    }
}
