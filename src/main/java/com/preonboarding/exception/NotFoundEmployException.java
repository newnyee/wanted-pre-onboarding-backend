package com.preonboarding.exception;

import org.springframework.http.HttpStatus;

public class NotFoundEmployException extends CommonException {

    public NotFoundEmployException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
