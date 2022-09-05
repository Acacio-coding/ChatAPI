package com.ifsc.chatapi.service;

import com.ifsc.chatapi.dto.UserDTO;
import com.ifsc.chatapi.model.UserModel;

public interface UserService {

    UserDTO get(String username);
    UserDTO create(UserDTO userDTO);
    UserDTO update(String username, UserDTO userDTO);
    UserDTO delete(String username);
}
