package com.preonboarding.exception;

import org.springframework.http.HttpStatus;

public class ApplyFailException extends CommonException{

    public ApplyFailException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
