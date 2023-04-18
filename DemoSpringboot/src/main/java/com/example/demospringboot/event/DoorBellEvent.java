package com.example.demospringboot.event;

import lombok.Data;
import org.springframework.context.ApplicationEvent;


public class DoorBellEvent extends ApplicationEvent {

    private String guestName;
    public DoorBellEvent(Object source, String guestName) {
        super(source);
        this.guestName = guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestName() {
        return guestName;
    }


}
