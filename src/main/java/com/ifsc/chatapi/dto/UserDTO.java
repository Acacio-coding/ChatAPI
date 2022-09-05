package com.ifsc.chatapi.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.ifsc.chatapi.utils.constants.ValidationMessages.*;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDTO {

    @NotBlank(message = BLANK_USERNAME)
    @Size(min = 4, max = 8, message = INVALID_USERNAME_SIZE)
    private String username;

    @NotBlank(message = BLANK_PASSWORD)
    @Size(min = 4, max = 8, message = INVALID_PASSWORD_SIZE)
    private String password;
}
