package com.zipdoridle.zachwitip.global.aop;


import com.zipdoridle.zachwitip.global.model.exception.ZachwiTipException;
import com.zipdoridle.zachwitip.global.model.response.ErrorResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@RestControllerAdvice
@Order(HIGHEST_PRECEDENCE)
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ZachwiTipException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(ZachwiTipException zachwiTipException){
        return new ResponseEntity<>(exceptionToErrorResponse(zachwiTipException), zachwiTipException.getStatus());
    }

    private ErrorResponse exceptionToErrorResponse(ZachwiTipException exception){
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .error(exception.getErrorCodes())
                .status(exception.getStatus().value())
                .build();
    }
}
