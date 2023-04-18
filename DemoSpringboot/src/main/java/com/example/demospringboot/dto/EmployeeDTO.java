package com.example.demospringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class EmployeeDTO {
    private Integer id;
    private String name;
    private String projectName;
    private String roleName;
    private String username;

    public EmployeeDTO(Integer id, String name, String projectName, String roleName, String username) {
        this.id = id;
        this.name = name;
        this.projectName = projectName;
        this.roleName = roleName;
        this.username = username;
    }
}
