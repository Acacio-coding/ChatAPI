package com.ifsc.chatapi.utils.constants;

public interface EndPoints {

    // Base
    String BASE_ENDPOINT = "/api/v1";

    // User
    String USER_BASE_ENDPOINT = "/users";
    String USER_GET_ENDPOINT = "/get/{username}";
    String USER_CREATION_ENDPOINT = "/create";
    String USER_UPDATE_ENDPOINT = "/update/{username}";
    String USER_DELETE_ENDPOINT = "/delete/{username}";

    // Message
    String MESSAGE_BASE_ENDPOINT = "/messages";
    String MESSAGE_SEND_TO_USER_ENDPOINT = "/send/user";
    String MESSAGE_SEND_TO_GROUP_ENDPOINT = "/send/group";

    // Group
    String GROUP_BASE_ENDPOINT = "/groups";
    String GROUP_GET_ENDPOINT = "/get/{groupId}";
    String GROUP_CREATION_ENDPOINT = "/create";
    String GROUP_UPDATE_ENDPOINT = "/update/{groupId}";
    String GROUP_DELETE_ENDPOINT = "/delete/{groupId}";
    String GROUP_REMOVE_PARTICIPANT = "/participant/remove/{groupId}";
    String GROUP_ADD_PARTICIPANT = "/participant/add/{groupId}";
}
