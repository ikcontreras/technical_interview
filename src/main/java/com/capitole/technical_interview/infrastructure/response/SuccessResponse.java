package com.capitole.technical_interview.infrastructure.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class SuccessResponse implements ApiResponse {
    private final int status;
    private final String message;
    private final Object data;

    public static SuccessResponse of(HttpStatus status, Object data) {
        return SuccessResponse.builder()
                .status(status.value())
                .message("Request processed successfully")
                .data(data)
                .build();
    }
}
