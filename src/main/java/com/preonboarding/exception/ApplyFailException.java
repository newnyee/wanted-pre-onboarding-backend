package com.preonboarding.exception;

import org.springframework.http.HttpStatus;

public class ApplyFailException extends CommonException{
    public ApplyFailException(HttpStatus httpStatus) {
        super(httpStatus);
    }

    public ApplyFailException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
