package com.ifsc.chatapi.service;

import com.ifsc.chatapi.advice.exception.ValidationException;
import com.ifsc.chatapi.dto.GroupDTO;
import com.ifsc.chatapi.model.GroupModel;
import com.ifsc.chatapi.repository.GroupRepository;
import com.ifsc.chatapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.ifsc.chatapi.utils.constants.EndPoints.*;
import static com.ifsc.chatapi.utils.constants.MQConstants.GROUP_EXCHANGE_BASE;
import static com.ifsc.chatapi.utils.constants.MQConstants.USER_QUEUE_BASE;
import static com.ifsc.chatapi.utils.constants.ValidationMessages.*;

@Service
@RequiredArgsConstructor
public class GroupServiceImplementation implements GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final RabbitMQService rabbitMQService;

    @Override
    public GroupModel get(int id) {
        var group = groupRepository.findById(id);

        if (group == null) {
            throw new ValidationException(
                    HttpStatus.NOT_FOUND,
                    GROUP_NOT_FOUND,
                    BASE_ENDPOINT + GROUP_BASE_ENDPOINT + GROUP_GET_ENDPOINT);
        }

        return group;
    }

    @Override
    public GroupModel create(GroupDTO groupDTO) {
        var newGroup = new GroupModel();
        BeanUtils.copyProperties(groupDTO, newGroup);

        this.groupRepository.save(newGroup);

        bindAllQueueToFanOut(newGroup);

        return newGroup;
    }

    @Override
    public GroupModel update(int id, GroupDTO groupDTO) {
        var group = groupRepository.findById(id);

        if (group == null) {
            throw new ValidationException(
                    HttpStatus.NOT_FOUND,
                    GROUP_NOT_FOUND,
                    BASE_ENDPOINT + GROUP_BASE_ENDPOINT + GROUP_UPDATE_ENDPOINT);
        }

        group.setName(groupDTO.getName());
        this.groupRepository.save(group);

        return group;
    }

    @Override
    public GroupModel delete(int id) {
        var group = groupRepository.findById(id);

        if (group == null) {
            throw new ValidationException(
                    HttpStatus.NOT_FOUND,
                    GROUP_NOT_FOUND,
                    BASE_ENDPOINT + GROUP_BASE_ENDPOINT + GROUP_DELETE_ENDPOINT);
        }

        this.groupRepository.delete(group);

        return group;
    }

    @Override
    public GroupModel removeParticipant(Integer participant, int id) {
        var group = groupRepository.findById(id);

        if (group == null) {
            throw new ValidationException(
                    HttpStatus.NOT_FOUND,
                    GROUP_NOT_FOUND,
                    BASE_ENDPOINT + GROUP_BASE_ENDPOINT + GROUP_REMOVE_PARTICIPANT);
        }

        try {
            group.getParticipants().remove(participant);
        } catch (Exception e) {
            throw new ValidationException(
                    HttpStatus.NOT_FOUND,
                    PARTICIPANT_NOT_FOUND,
                    GROUP_BASE_ENDPOINT + GROUP_REMOVE_PARTICIPANT
            );
        }

        this.groupRepository.save(group);

        return group;
    }

    @Override
    public GroupModel addParticipant(Integer participant, int id) {
        var group = groupRepository.findById(id);

        if (group == null) {
            throw new ValidationException(
                    HttpStatus.NOT_FOUND,
                    GROUP_NOT_FOUND,
                    BASE_ENDPOINT + GROUP_BASE_ENDPOINT + GROUP_ADD_PARTICIPANT);
        }

        if (!this.userRepository.existsById(Long.valueOf(participant))) {
            throw new ValidationException(
                    HttpStatus.NOT_FOUND,
                    PARTICIPANT_DOES_NOT_EXISTS,
                    BASE_ENDPOINT + GROUP_BASE_ENDPOINT + GROUP_ADD_PARTICIPANT);
        }

        try {
            group.getParticipants().add(participant);
        } catch (Exception e) {
            throw new ValidationException(
                    HttpStatus.CONFLICT,
                    PARTICIPANT_ALREADY_EXISTS,
                    BASE_ENDPOINT + GROUP_BASE_ENDPOINT + GROUP_ADD_PARTICIPANT);
        }

        this.groupRepository.save(group);

        bindQueueToFanOut(participant);

        return group;
    }


    private void bindAllQueueToFanOut(GroupModel groupModel) {
        groupModel.getParticipants().forEach(participant -> {
            String queueName = USER_QUEUE_BASE + participant;
            this.rabbitMQService.bindQueueToFanOut(queueName);
        });
    }

    private void bindQueueToFanOut(Integer participant) {
        String queueName = USER_QUEUE_BASE + participant;
        this.rabbitMQService.bindQueueToFanOut(queueName);
    }
}
