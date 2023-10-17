package com.preonboarding.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import java.sql.Timestamp;

@Getter
public class CommonResponseDto {
    private Timestamp timestamp;

    private Integer status;

    private String message = "success";

    private Object data;


    public CommonResponseDto(HttpStatus httpStatus) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.status = httpStatus.value();
    }

    public CommonResponseDto(HttpStatus httpStatus, Object object) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.status = httpStatus.value();
        this.data = object;
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
        this.data = object;
    }
}

