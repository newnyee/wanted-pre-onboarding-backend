package com.preonboarding.exception;

import org.springframework.http.HttpStatus;

public class FailToDeleteEmployException extends CommonException{
    public FailToDeleteEmployException(HttpStatus httpStatus) {
        super(httpStatus);
    }

    public FailToDeleteEmployException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
