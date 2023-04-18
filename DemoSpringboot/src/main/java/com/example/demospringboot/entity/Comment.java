package com.example.demospringboot.entity;


import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comment")
@Getter
@Setter
@NoArgsConstructor
public class Comment {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

}
