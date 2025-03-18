package com.capitole.technical_interview.infrastructure.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ErrorResponse implements ApiResponse {
    private final int status;
    private final String message;
    private final String error;

    public static ErrorResponse of(HttpStatus status, String error, String message) {
        return ErrorResponse.builder()
                .status(status.value())
                .error(error)
                .message(message)
                .build();
    }
}
