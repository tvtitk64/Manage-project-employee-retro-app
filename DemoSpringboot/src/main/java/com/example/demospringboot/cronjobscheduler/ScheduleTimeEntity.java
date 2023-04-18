package com.example.demospringboot.cronjobscheduler;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "cronjobandscheduling")
public class ScheduleTimeEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    public ScheduleTimeEntity(String value) {
        this.value = value;
    }
}
