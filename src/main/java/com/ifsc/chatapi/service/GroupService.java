package com.ifsc.chatapi.service;

import com.ifsc.chatapi.dto.GroupDTO;
import com.ifsc.chatapi.model.GroupModel;

public interface GroupService {

    GroupModel get(int id);
    GroupModel create(GroupDTO groupDTO);
    GroupModel update(int id, GroupDTO groupDTO);
    GroupModel delete(int id);
    GroupModel removeParticipant(Integer participant, int id);
    GroupModel addParticipant(Integer participant, int id);
}
