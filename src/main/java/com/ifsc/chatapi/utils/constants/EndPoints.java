package com.ifsc.chatapi.utils.constants;

public interface EndPoints {
    //Base
    String BASE_ENDPOINT = "/api/v1";

    //User
    String USER_BASE_ENDPOINT = "/users";
    String USER_GET_ENDPOINT = "/get/{username}";
    String USER_CREATION_ENDPOINT = "/create";
    String USER_UPDATE_ENDPOINT = "/update/{username}";
    String USER_DELETE_ENDPOINT = "/delete/{username}";
}
