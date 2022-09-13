package com.ifsc.chatapi.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import static com.ifsc.chatapi.utils.constants.ValidationMessages.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageToGroupDTO {

    @NotBlank(message = BLANK_SENDER)
    @Size(min = 4, max = 8, message = INVALID_SENDER_SIZE)
    private String sender;

    @NotNull(message = NULL_GROUP)
    private Integer groupId;

    @NotBlank(message = BLANK_CONTENT)
    private String content;

    @NotBlank(message = BLANK_TIMESTAMP)
    private String timestamp;
}
