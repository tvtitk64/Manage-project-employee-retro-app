package com.example.demospringboot.transactional;

import com.example.demospringboot.dto.EmployeeDTO;
import com.example.demospringboot.entity.Employee;
import com.example.demospringboot.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TransactionalService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public ResponseEntity<?> checkTransactional(Integer id, String name) throws Exception {
        try {
            Employee employee = employeeRepository.findById(id).get();
            employee.setUsername(name);
            employeeRepository.save(employee);
            employeeRepository.deleteById(id+1);
            return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(employee, EmployeeDTO.class));
        } catch (Exception e) {
            throw new Error("test");
        }
    }
}
