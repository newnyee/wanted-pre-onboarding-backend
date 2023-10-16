package com.preonboarding.common;

import org.springframework.http.HttpStatus;
import java.sql.Timestamp;

public class CommonResponseDto {
    private Timestamp timestamp;

    private Integer status;

    private String message = "success";

    private Object object;


    public CommonResponseDto(HttpStatus httpStatus) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.status = httpStatus.value();
    }

    public CommonResponseDto(HttpStatus httpStatus, Object object) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.status = httpStatus.value();
        this.object = object;
    }

    public CommonResponseDto(HttpStatus httpStatus, String message) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.status = httpStatus.value();
        this.message = message;
    }

    public CommonResponseDto(HttpStatus httpStatus, String message, Object object) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.status = httpStatus.value();
        this.message = message;
        this.object = object;
    }
}

