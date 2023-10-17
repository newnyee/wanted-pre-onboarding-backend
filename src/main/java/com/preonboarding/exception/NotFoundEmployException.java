package com.preonboarding.exception;

import org.springframework.http.HttpStatus;

public class NotFoundEmployException extends CommonException {

    public NotFoundEmployException(HttpStatus httpStatus) {
        super(httpStatus);
    }

    public NotFoundEmployException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
