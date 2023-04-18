package com.example.demospringboot.scope;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class BeanLifeCycle {
    @PostConstruct
    public void postConstruct() {
        System.out.println("Post construct");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("Pre destroy");
    }
}
