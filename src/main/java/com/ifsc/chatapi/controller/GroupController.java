package com.ifsc.chatapi.controller;

import com.ifsc.chatapi.dto.GroupDTO;
import com.ifsc.chatapi.dto.ParticipantDTO;
import com.ifsc.chatapi.model.GroupModel;
import com.ifsc.chatapi.service.GroupServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.ifsc.chatapi.utils.constants.EndPoints.*;
import static com.ifsc.chatapi.utils.constants.RestConstants.GROUP;

@RestController
@RequestMapping(GROUP_BASE_ENDPOINT)
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class GroupController {

    private final GroupServiceImplementation groupService;

    @GetMapping(GROUP_GET_ENDPOINT)
    public ResponseEntity<GroupModel> get(@PathVariable(value = GROUP) int id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.groupService.get(id));
    }

    @PostMapping(GROUP_CREATION_ENDPOINT)
    public ResponseEntity<GroupModel> create(@RequestBody @Validated GroupDTO groupDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.groupService.create(groupDTO));
    }

    @PutMapping(GROUP_UPDATE_ENDPOINT)
    public ResponseEntity<GroupModel> update(@PathVariable(value = GROUP) int id,
                                            @RequestBody @Validated GroupDTO groupDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(this.groupService.update(id, groupDTO));
    }

    @DeleteMapping(GROUP_DELETE_ENDPOINT)
    public ResponseEntity<GroupModel> delete(@PathVariable(value = GROUP) int id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.groupService.delete(id));
    }

    @PostMapping(GROUP_ADD_PARTICIPANT)
    public ResponseEntity<GroupModel> addParticipant(@PathVariable(value = GROUP) int id,
                                                     @RequestBody @Validated ParticipantDTO participantDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(this.groupService
                .addParticipant(participantDTO.getParticipant(), id));
    }

    @DeleteMapping(GROUP_REMOVE_PARTICIPANT)
    public ResponseEntity<GroupModel> removeParticipant(@PathVariable(value = GROUP) int id,
                                                        @RequestBody @Validated ParticipantDTO participantDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(this.groupService
                .removeParticipant(participantDTO.getParticipant(), id));
    }
}
