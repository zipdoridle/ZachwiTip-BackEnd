package com.zipdoridle.zachwitip.global.model.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ZachwiTipException extends RuntimeException{

    private String message;
    private HttpStatus status;
    private ErrorCodes errorCodes;

    public ZachwiTipException(String message, HttpStatus status, ErrorCodes errorCodes) {
        super(message);
        this.message = message;
        this.status = status;
        this.errorCodes = errorCodes;
    }

    public String getErrorCodes(){
        return String.format("Error-%03d", errorCodes.ordinal()+1);
    }
}
