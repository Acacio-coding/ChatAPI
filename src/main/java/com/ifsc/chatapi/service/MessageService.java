package com.ifsc.chatapi.service;

import com.ifsc.chatapi.dto.MessageToGroupDTO;
import com.ifsc.chatapi.dto.MessageToUserDTO;

public interface MessageService {

    void sendToUser(MessageToUserDTO message);
    void sendToGroup(MessageToGroupDTO message);
}
