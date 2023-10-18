package com.preonboarding.common.handler;

import com.preonboarding.common.ExceptionResponse;
import com.preonboarding.exception.CommonException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ResponseExceptionHandler {

    /**
     * 유효성 검사 예외 핸들러
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ExceptionResponse methodArgumentValidExceptionHandler(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.error("[methodArgumentValidExceptionHandler] ex", e);
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, e.getBindingResult(), request.getRequestURI());
    }

    /**
     * 유효성 검사 예외 핸들러
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ExceptionResponse httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e, HttpServletRequest request) {
        log.error("[httpMessageNotReadableExceptionHandler] ex", e);
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, e.getMessage(), request.getRequestURI());
    }

    /**
     * repository, service 예외 핸들러
     */
    @ExceptionHandler(CommonException.class)
    protected ResponseEntity<ExceptionResponse> commonExceptionHandler(CommonException e, HttpServletRequest request) {
        log.error("[commonExceptionHandler] ex", e);
        return new ResponseEntity<>(
            new ExceptionResponse(e.getHttpStatus(), e.getMessage(), request.getRequestURI()), e.getHttpStatus()
        );
    }
}