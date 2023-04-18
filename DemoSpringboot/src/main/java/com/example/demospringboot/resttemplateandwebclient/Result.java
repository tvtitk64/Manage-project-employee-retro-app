package com.example.demospringboot.resttemplateandwebclient;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Result {
    private String firstName;
    private String lastName;
    private String email;
}
