package com.ifsc.chatapi.controller;

import com.ifsc.chatapi.dto.UserDTO;
import com.ifsc.chatapi.service.UserServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.ifsc.chatapi.utils.constants.EndPoints.*;
import static com.ifsc.chatapi.utils.constants.RestConstants.USERNAME;

@RestController
@RequestMapping(USER_BASE_ENDPOINT)
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImplementation userService;

    @GetMapping(USER_GET_ENDPOINT)
    public ResponseEntity<UserDTO> get(@PathVariable(value = USERNAME) String username) {
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.get(username));
    }

    @PostMapping(USER_CREATION_ENDPOINT)
    public ResponseEntity<UserDTO> create(@RequestBody @Validated UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.create(userDTO));
    }

    @PutMapping(USER_UPDATE_ENDPOINT)
    public ResponseEntity<UserDTO> update(@PathVariable(value = USERNAME) String username,
                                          @RequestBody @Validated UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.update(username, userDTO));
    }

    @DeleteMapping(USER_DELETE_ENDPOINT)
    public ResponseEntity<UserDTO> delete(@PathVariable(value = USERNAME) String username) {
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.delete(username));
    }
}
