package com.example.demospringboot.exception;

import com.example.demospringboot.constant.ResponseCode;
import com.example.demospringboot.dto.CommonResponseDTO;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import java.util.Date;

@RestControllerAdvice
public class TokenControllerAdvice {

    @ExceptionHandler(value = TokenRefreshException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public CommonResponseDTO<String> handleTokenRefreshException(TokenRefreshException ex, WebRequest request) {
        return new CommonResponseDTO<>(ResponseCode.FAIL, ex.getMessage());
    }
}