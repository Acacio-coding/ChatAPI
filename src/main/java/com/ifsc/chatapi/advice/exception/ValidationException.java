package com.ifsc.chatapi.advice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class ValidationException extends RuntimeException {
    private final HttpStatus status;
    private final String message;
    private final String path;
}
