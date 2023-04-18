package com.example.demospringboot.query;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CountEmployeeRole {
    private Integer role_id;
    private Long total;

    public CountEmployeeRole(Integer role_id, Long total) {
        this.role_id = role_id;
        this.total = total;
    }
}
