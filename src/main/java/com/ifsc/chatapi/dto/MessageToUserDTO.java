package com.ifsc.chatapi.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.ifsc.chatapi.utils.constants.ValidationMessages.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageToUserDTO {

    @NotBlank(message = BLANK_SENDER)
    @Size(min = 4, max = 8, message = INVALID_SENDER_SIZE)
    private String sender;

    @NotBlank(message = BLANK_RECEIVER)
    @Size(min = 4, max = 8, message = INVALID_RECEIVER_SIZE)
    private String receiver;

    @NotBlank(message = BLANK_CONTENT)
    private String content;

    @NotBlank(message = BLANK_TIMESTAMP)
    private String timestamp;
}
