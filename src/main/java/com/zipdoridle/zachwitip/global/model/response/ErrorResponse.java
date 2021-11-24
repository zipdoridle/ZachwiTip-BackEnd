package com.zipdoridle.zachwitip.global.model.response;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {
    private String error;
    private String message;
    private int status;
    private LocalDateTime time = LocalDateTime.now();

    @Builder
    public ErrorResponse(String error, String message, int status) {
        this.error = error;
        this.message = message;
        this.status = status;
    }
}
