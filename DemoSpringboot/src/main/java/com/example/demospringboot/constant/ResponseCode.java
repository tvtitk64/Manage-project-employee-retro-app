package com.example.demospringboot.constant;

import lombok.Data;


public enum ResponseCode {
    SUCCESS("SUCCESS"),
    FAIL("FAIL");

    private final String code;

    ResponseCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }


}
