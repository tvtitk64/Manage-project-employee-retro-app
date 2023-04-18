package com.example.demospringboot.security.services;

import com.example.demospringboot.entity.Employee;
import com.example.demospringboot.repository.EmployeeRepository;
import com.example.demospringboot.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.annotation.Inherited;

//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Employee employee = employeeRepository.findEmployeeByUsername(username);
//        return UserDetailsImpl.build(employee);
//    }
//}
