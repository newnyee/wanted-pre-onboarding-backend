package com.preonboarding.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.sql.Timestamp;

/**
 * 예외발생 시 공통 응답 DTO
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
    private Timestamp timestamp;

    private Integer status;

    private String error;

    private String message;

    private String path;

    public ExceptionResponse(HttpStatus httpStatus, BindingResult bindingResult, String path) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.status = httpStatus.value();
        this.error = httpStatus.name();
        this.message = createErrorMessage(bindingResult);
        this.path = path;
    }

    public ExceptionResponse(HttpStatus httpStatus, String message, String path) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.status = httpStatus.value();
        this.error = httpStatus.name();
        this.message = message;
        this.path = path;
    }

    private static String createErrorMessage(BindingResult bindingResult) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean isFirst = true;

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            if (!isFirst) {
                stringBuilder.append(", ");
            } else {
                isFirst = false;
            }

            stringBuilder.append("[");
            stringBuilder.append(fieldError.getField());
            stringBuilder.append("]은(는) ");
            stringBuilder.append(fieldError.getDefaultMessage());
        }

        return stringBuilder.toString();
    }
}