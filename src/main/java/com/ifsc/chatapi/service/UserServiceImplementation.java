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
import static com.ifsc.chatapi.utils.constants.ValidationMessages.*;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDTO get(String username) {
        var user = this.userRepository.findByUsername(username);

        if (user == null) {
            throw new ValidationException(
                    HttpStatus.NOT_FOUND,
                    USER_NOT_FOUND,
                    BASE_ENDPOINT + USER_BASE_ENDPOINT + USER_GET_ENDPOINT);
        }

        var toReturn = new UserDTO();
        BeanUtils.copyProperties(user, toReturn);

        return toReturn;
    }

    @Override
    public UserDTO create(UserDTO userDTO) {
        if (this.userRepository.existsByUsername(userDTO.getUsername())) {
            throw new ValidationException(
                    HttpStatus.CONFLICT,
                    USERNAME_ALREADY_TAKEN,
                    BASE_ENDPOINT + USER_BASE_ENDPOINT + USER_CREATION_ENDPOINT);
        }

        var newUser = new UserModel();
        BeanUtils.copyProperties(userDTO, newUser);

        this.userRepository.save(newUser);

        return userDTO;
    }

    @Override
    public UserDTO update(String username, UserDTO userDTO) {
        var user = this.userRepository.findByUsername(username);

        if (user == null) {
            throw new ValidationException(
                    HttpStatus.NOT_FOUND,
                    USER_NOT_FOUND,
                    BASE_ENDPOINT + USER_BASE_ENDPOINT + USER_UPDATE_ENDPOINT);
        }

        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());

        this.userRepository.save(user);

        return userDTO;
    }

    @Override
    public UserDTO delete(String username) {
        var user = this.userRepository.findByUsername(username);

        if (user == null) {
            throw new ValidationException(
                    HttpStatus.NOT_FOUND,
                    USER_NOT_FOUND,
                    BASE_ENDPOINT + USER_BASE_ENDPOINT + USER_DELETE_ENDPOINT);
        }

        this.userRepository.delete(user);

        var toReturn = new UserDTO();
        BeanUtils.copyProperties(user, toReturn);

        return toReturn;
    }
}
