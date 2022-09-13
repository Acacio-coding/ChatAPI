package com.ifsc.chatapi.service;

import com.ifsc.chatapi.dto.UserDTO;
import com.ifsc.chatapi.model.UserModel;

public interface UserService {

    UserModel get(String username);
    UserModel create(UserDTO userDTO);
    UserModel update(String username, UserDTO userDTO);
    UserModel delete(String username);
}
