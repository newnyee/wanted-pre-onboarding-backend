package com.preonboarding.exception;

import org.springframework.http.HttpStatus;

public class NotFoundUserException extends CommonException{

    public NotFoundUserException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
