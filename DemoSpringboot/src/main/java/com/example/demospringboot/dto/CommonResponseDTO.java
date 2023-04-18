package com.example.demospringboot.dto;

import com.example.demospringboot.constant.ResponseCode;

public class CommonResponseDTO<T> {
    private ResponseCode code;

    private T data;

    public CommonResponseDTO() {
        this(ResponseCode.SUCCESS);
    }

    public CommonResponseDTO(ResponseCode status) {
        this(status, null);
    }

    public CommonResponseDTO(T data) {
        this(ResponseCode.SUCCESS, data);
    }

    public CommonResponseDTO(ResponseCode status, T data) {
        this.code = status;
        this.data = data;
    }
}
