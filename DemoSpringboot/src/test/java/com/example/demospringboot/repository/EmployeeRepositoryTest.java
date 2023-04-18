package com.example.demospringboot.repository;

import com.example.demospringboot.entity.Employee;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@ExtendWith(MockitoExtension.class)
@DataJpaTest
//@SpringBootTest
public class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;




//    @Test
//    public void testGetAllEmployee() {
//        List<Employee> employees = employeeRepository.findAll();
//        assertEquals(4, employees.size());
//    }

    private Employee createEmployee() {
        Employee employee = new Employee();
        employee.setId(10);
        employee.setName("NCC");
        employee.setUsername("ncc");
        employee.setEmail("ncc@asia.com");
        return employee;
    }

    @Test
    public void testSave() {
        Employee employee = new Employee(10, "NCC");
        employeeRepository.save(employee);
        Employee foundEmployee = employeeRepository.findById(employee.getId()).get();
        assertEquals(employee.getId(), foundEmployee.getId());
    }
    @Test
    public void testSave2() {

        employeeRepository.save(new Employee(10, "NCC"));
        assertEquals(employeeRepository.findById(10).get().getId(),10);
    }

//    @Test
//    public void testFindEmployeeByRoleId() {
//        Employee employee = createEmployee();
//        employeeRepository.save(employee);
//        Optional<Employee> test = employeeRepository.findEmployeeByUsername("ncc");
//        assertThat(employee.getUsername(), equalTo(test.getUsername()));
//    }

}
