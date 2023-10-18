package com.preonboarding.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 커스텀 exception을 관리할 상위 exception 추가
 */
@Getter
public class CommonException extends RuntimeException {

    private HttpStatus httpStatus;

    public CommonException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
