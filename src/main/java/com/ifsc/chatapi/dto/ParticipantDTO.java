package com.ifsc.chatapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

import static com.ifsc.chatapi.utils.constants.ValidationMessages.NULL_PARTICIPANT;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantDTO {

    @NotNull(message = NULL_PARTICIPANT)
    private Integer participant;
}
