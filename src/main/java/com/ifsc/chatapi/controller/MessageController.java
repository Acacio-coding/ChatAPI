package com.ifsc.chatapi.controller;

import com.ifsc.chatapi.dto.MessageToGroupDTO;
import com.ifsc.chatapi.dto.MessageToUserDTO;
import com.ifsc.chatapi.service.MessageServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.ifsc.chatapi.utils.constants.EndPoints.*;


@RestController
@RequestMapping(MESSAGE_BASE_ENDPOINT)
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class MessageController {

    private final MessageServiceImplementation messageService;

    @PostMapping(MESSAGE_SEND_TO_USER_ENDPOINT)
    public ResponseEntity<?> sendMessageToUser(@RequestBody @Validated MessageToUserDTO messageToUserDTO) {
        messageService.sendToUser(messageToUserDTO);
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @PostMapping(MESSAGE_SEND_TO_GROUP_ENDPOINT)
    public ResponseEntity<?> sendMessageToGroup(@RequestBody @Validated MessageToGroupDTO messageToGroupDTO) {
        messageService.sendToGroup(messageToGroupDTO);
        return ResponseEntity.status(HttpStatus.OK).body("");
    }
}
