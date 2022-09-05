package com.ifsc.chatapi.utils.constants;

public interface ValidationMessages {
    String BLANK_USERNAME = "Username cannot be blank";
    String INVALID_USERNAME_SIZE = "Username must be between 4 and 8 characters";
    String BLANK_PASSWORD = "Password cannot be blank";
    String INVALID_PASSWORD_SIZE = "Password must be between 4 and 8 characters";
    String USER_NOT_FOUND = "The user with provided username was not found.";
    String USERNAME_ALREADY_TAKEN = "The provided username was already taken.";
}
