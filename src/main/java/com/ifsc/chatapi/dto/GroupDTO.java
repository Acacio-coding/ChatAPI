package com.ifsc.chatapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

import static com.ifsc.chatapi.utils.constants.ValidationMessages.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupDTO {

    @NotBlank(message = BLANK_GROUP_NAME)
    @Size(min = 3, message = INVALID_GROUP_NAME)
    private String name;

    @Size(min = 1, message = INVALID_PARTICIPANTS_SIZE)
    private Set<Integer> participants;
}
