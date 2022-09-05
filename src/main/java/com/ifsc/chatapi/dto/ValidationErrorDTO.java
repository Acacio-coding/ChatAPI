package com.ifsc.chatapi.dto;

import java.time.ZonedDateTime;

public record ValidationErrorDTO(int status, ZonedDateTime timestamp, String message, String path) {
}
