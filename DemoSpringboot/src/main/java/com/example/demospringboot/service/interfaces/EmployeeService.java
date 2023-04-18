package com.example.demospringboot.service.interfaces;

import com.example.demospringboot.dto.EmployeeDTO;
import com.example.demospringboot.entity.Employee;
import com.example.demospringboot.entity.Update;
import com.example.demospringboot.query.CountEmployeeByRoleName;
import com.example.demospringboot.query.CountEmployeeRole;
import com.example.demospringboot.query.ICountEmployeeRole;
import com.example.demospringboot.resttemplateandwebclient.Result;
import com.example.demospringboot.resttemplateandwebclient.ResultList;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public interface EmployeeService extends UserDetailsService {
    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeById(Integer id);

    String createEmployee(Employee employee);

    String updateEmployee(Integer id, Update update);

    String deleteEmployee(Integer id);

    String deleteAllEmployee();

    List<EmployeeDTO> getByRoleId(Integer id);
    List<EmployeeDTO> getByRoleName(String str);

    List<EmployeeDTO> getByProjectId(Integer id);
    List<EmployeeDTO> getByProjectName(String str);
    List<EmployeeDTO> getEmployeeByName(String str);

//    Flux<Result> getUserNcc();
//
//    ResultList getNccUsers() throws JsonProcessingException;

    Page<Employee> getAllEmployeeWithPaging(Pageable pageable);
    Slice<Employee> getAllEmployeeWithSlice(Pageable pageable);

    List<CountEmployeeRole> countTotalEmployeeByRole();
    List<ICountEmployeeRole> countTotalEmployeeByRoleInterface();
    List<CountEmployeeByRoleName> getEmployeeByRoleName();

    UserDetails loadUserByUsername(String Username) throws UsernameNotFoundException;


}
