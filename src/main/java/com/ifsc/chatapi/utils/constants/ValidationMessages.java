package com.ifsc.chatapi.utils.constants;

public interface ValidationMessages {

    // User
    String BLANK_USERNAME = "Username cannot be blank";
    String INVALID_USERNAME_SIZE = "Username must be between 4 and 8 characters long";
    String BLANK_PASSWORD = "Password cannot be blank";
    String INVALID_PASSWORD_SIZE = "Password must be between 4 and 8 characters long";
    String USER_NOT_FOUND = "The user with provided username was not found";
    String USERNAME_ALREADY_TAKEN = "The provided username was already taken.";

    // Message
    String BLANK_SENDER = "The sender cannot be blank";
    String INVALID_SENDER_SIZE = "Sender username must be between 4 and 8 characters long";
    String SENDER_NOT_FOUND = "The sender user with provided username was not found";
    String BLANK_RECEIVER = "The receiver cannot be blank";
    String INVALID_RECEIVER_SIZE = "Receiver username must be between 4 and 8 characters long";
    String RECEIVER_NOT_FOUND = "The receiver user with provided username was not found";
    String BLANK_CONTENT = "The content cannot be blank";
    String NULL_GROUP = "The group id cannot be null";
    String INVALID_PARTICIPANTS_SIZE = "The number of participants must be at least 2";
    String BLANK_TIMESTAMP = "The timestamp cannot be blank";

    // Group
    String BLANK_GROUP_NAME = "The group name cannot be blank";
    String INVALID_GROUP_NAME = "Group name must be at least 3 characters long";
    String GROUP_NOT_FOUND = "The group with provided id was not found";
    String PARTICIPANT_NOT_FOUND = "The participant with provided id was not found in the group with provided id";
    String PARTICIPANT_ALREADY_EXISTS = "The participant with provided id already exists in the group with provided id";
    String PARTICIPANT_DOES_NOT_EXISTS = "The participant with provided id doesn't exist";
    String NULL_PARTICIPANT = "The participant id cannot be null";
}
