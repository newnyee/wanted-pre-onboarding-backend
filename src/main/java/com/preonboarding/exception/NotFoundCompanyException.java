package com.preonboarding.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundCompanyException extends CommonException {

    public NotFoundCompanyException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}