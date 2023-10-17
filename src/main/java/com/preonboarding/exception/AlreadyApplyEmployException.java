package com.preonboarding.exception;

import org.springframework.http.HttpStatus;

public class AlreadyApplyEmployException extends CommonException {

    public AlreadyApplyEmployException(HttpStatus httpStatus) {
        super(httpStatus);
    }

    public AlreadyApplyEmployException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
