package com.example.demospringboot.event;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MyDog {
    @EventListener
    @Async
    public void doorBellEventListener(DoorBellEvent doorBellEvent) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName()+ ": Cho ngu day!!");
        System.out.println(String.format("%s: GO Go! co ng ten la %s go cua", Thread.currentThread().getName(), doorBellEvent.getGuestName()));
    }
}
