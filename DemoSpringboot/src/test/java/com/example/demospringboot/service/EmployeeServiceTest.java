package com.example.demospringboot.service;

import com.example.demospringboot.entity.Employee;
import com.example.demospringboot.exception.NotFoundEx;
import com.example.demospringboot.repository.EmployeeRepository;
import com.example.demospringboot.service.interfaces.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @Mock
    EmployeeRepository employeeRepository ;// = Mockito.mock(IUserRepository.class);
    @InjectMocks
    EmployeeServiceImpl employeeService;

    @Mock
    ModelMapper modelMapper;


//    @Test
//    public void testGetByUserName(){
//        when(employeeRepository.findEmployeeByUsername("UserName")).thenReturn(new Employee("UserName","Password"));
//        when(userRepository.existsByUserName("UserName")).thenReturn(true);
//        User user = userService.getByUserName("UserName");
//        assertEquals("Password",user.getPassWord());
//        assertNotNull(user);
//    }

    @Test
    public void testGetAll(){
        employeeService.getAllEmployees();
        verify(employeeRepository).findAll();
        //not finished yet
    }

}
