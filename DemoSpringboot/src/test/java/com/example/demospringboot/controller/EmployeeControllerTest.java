package com.example.demospringboot.controller;

import com.example.demospringboot.dto.EmployeeDTO;
import com.example.demospringboot.service.EmployeeServiceImpl;
import com.example.demospringboot.service.interfaces.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
//    @Autowired
    private EmployeeServiceImpl employeeService;

    private List<EmployeeDTO> getEmployees = new ArrayList<>();

    @BeforeEach
    public void setup() {
        EmployeeDTO dto1 = new EmployeeDTO(1, "toan1", "java1", "ROLE_INTERN1", "vutoan1");
        EmployeeDTO dto2 = new EmployeeDTO(2, "toan2", "java2", "ROLE_INTERN2", "vutoan2");
        EmployeeDTO dto3 = new EmployeeDTO(3, "toan3", "java3", "ROLE_INTERN3", "vutoan3");
        EmployeeDTO dto4 = new EmployeeDTO(4, "toan4", "java4", "ROLE_INTERN4", "vutoan4");
        getEmployees.add(dto1);
        getEmployees.add(dto2);
        getEmployees.add(dto3);
        getEmployees.add(dto4);
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        given(employeeService.getAllEmployees()).willReturn(getEmployees);
        this.mockMvc.perform(get("/employee/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)));
    }

}
