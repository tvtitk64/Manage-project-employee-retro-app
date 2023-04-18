package com.example.demospringboot.controller;


import com.example.demospringboot.dto.EmployeeDTO;
import com.example.demospringboot.entity.Employee;
import com.example.demospringboot.entity.Update;
import com.example.demospringboot.repository.EmployeeRepository;
import com.example.demospringboot.resttemplateandwebclient.ResultList;
import com.example.demospringboot.service.interfaces.EmployeeService;
import com.example.demospringboot.transactional.TransactionalService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Collections;
import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    private TransactionalService transactionalService;

    @GetMapping("/employee/getAll")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employeeDtos = employeeService.getAllEmployees();
        return new ResponseEntity<>(employeeDtos, HttpStatus.OK) ;
    }

    @GetMapping("/employee/id/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") Integer id) {
        EmployeeDTO employeeDto = employeeService.getEmployeeById(id);
        if (employeeDto != null) {
            return new ResponseEntity<>(employeeDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/employee/name/{name}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeeByName(@PathVariable("name") String str) {
        List<EmployeeDTO> employeeDtos = employeeService.getEmployeeByName(str);
        return new ResponseEntity<>(employeeDtos, HttpStatus.OK) ;
    }

    @PostMapping("/employee")
    public String createEmployee(@RequestBody Employee employee) {
        String employeeDto = employeeService.createEmployee(employee);
        return employeeDto;
    }

    @PutMapping("/employee/{id}")
    public String updateEmployee(@PathVariable("id") Integer id, @RequestBody Update update) {
        String employeeDto = employeeService.updateEmployee(id, update);
        return employeeDto;
    }

    @DeleteMapping("/employee/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id) {
        String employeeDTO = employeeService.deleteEmployee(id);
        return employeeDTO;
    }

    @DeleteMapping("/employee")
    public String deleteAllEmployees() {
        String employeeDTO = employeeService.deleteAllEmployee();
        return employeeDTO;
    }

    @GetMapping("/employee/role/id/{role_id}")
    public ResponseEntity<List<EmployeeDTO>> getByRoleId(@PathVariable("role_id") Integer id) {
        List<EmployeeDTO> employeeDtos = employeeService.getByRoleId(id);
        return new ResponseEntity<>(employeeDtos, HttpStatus.OK) ;
    }

    @GetMapping("/employee/role/name/{role_name}")
    public ResponseEntity<List<EmployeeDTO>> getByRoleName(@PathVariable("role_name") String str) {
        List<EmployeeDTO> employeeDtos = employeeService.getByRoleName(str);
        return new ResponseEntity<>(employeeDtos, HttpStatus.OK) ;
    }

    @GetMapping("/employee/project/id/{project_id}")
    public ResponseEntity<List<EmployeeDTO>> getByProjectId(@PathVariable("project_id") Integer id) {
        List<EmployeeDTO> employeeDtos = employeeService.getByProjectId(id);
        return new ResponseEntity<>(employeeDtos, HttpStatus.OK) ;
    }

    @GetMapping("/employee/project/name")
    public ResponseEntity<List<EmployeeDTO>> getByProjectName(@RequestParam(value = "param", required = true) String str) {
        List<EmployeeDTO> employeeDtos = employeeService.getByProjectName(str);
        return new ResponseEntity<>(employeeDtos, HttpStatus.OK) ;
    }

    @GetMapping("/getEmployeeByEvenId/id/{id}")
    public ResponseEntity<?> checkTransactional(@PathVariable Integer id, @RequestBody EmployeeDTO name) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(transactionalService.checkTransactional(id, name.getUsername()));
    }

    @GetMapping("/paging")
    public ResponseEntity<?> getAllEmployeeWithPaging(@RequestParam Integer number, @RequestParam int size){
        Pageable pageable = PageRequest.of(number,size);
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAllEmployeeWithPaging(pageable));
    }
    @GetMapping("/slice")
    public ResponseEntity<?> getAllEmployeeWithSlice(@RequestParam Integer number){
        Pageable pageable = PageRequest.of(number,10);
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAllEmployeeWithSlice(pageable));
    }
    @GetMapping("/constructors")
    public ResponseEntity<?> countTotalEmployeeByRole(){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.countTotalEmployeeByRole());
    }
    @GetMapping("/projections")
    public ResponseEntity<?> countTotalEmployeeByRoleInterface(){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.countTotalEmployeeByRoleInterface());
    }
    @GetMapping("/join")
    public ResponseEntity<?> getEmployeeByRoleName(){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployeeByRoleName());
    }

//    @GetMapping("/client_ncc")
//    public ResultList findAll() throws JsonProcessingException {
//        return employeeService.getNccUsers();
//    }

//    WebClient client = WebClient.builder()
//            .baseUrl("http://localhost:8080")
//            .defaultCookie("cookieKey", "cookieValue")
//            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//            .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
//            .build();
}
