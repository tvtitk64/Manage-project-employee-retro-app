package com.example.demospringboot.query;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CountEmployeeByRoleName {
    
    private String role_name;
    
    private Integer employee_id;
    
    public CountEmployeeByRoleName(String role_name, Integer employee_id) {
        this.role_name = role_name;
        this.employee_id = employee_id;
    }
}
