package com.ifsc.chatapi.advice;

import com.ifsc.chatapi.advice.exception.ValidationException;
import com.ifsc.chatapi.dto.ValidationErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ValidationErrorDTO> handleValidationException(ValidationException exception) {
        return ResponseEntity.status(exception.getStatus())
                .body(new ValidationErrorDTO(
                        exception.getStatus().value(),
                        ZonedDateTime.now(),
                        exception.getMessage(),
                        exception.getPath()));
    }
}
