package com.example.demospringboot.service;

import com.example.demospringboot.dto.EmployeeDTO;
import com.example.demospringboot.entity.Employee;
import com.example.demospringboot.entity.Project;
import com.example.demospringboot.entity.Role;
import com.example.demospringboot.entity.Update;
import com.example.demospringboot.query.CountEmployeeByRoleName;
import com.example.demospringboot.query.CountEmployeeRole;
import com.example.demospringboot.query.ICountEmployeeRole;
import com.example.demospringboot.repository.EmployeeRepository;
import com.example.demospringboot.repository.ProjectRepository;
import com.example.demospringboot.repository.RoleRepository;
import org.springframework.data.domain.Pageable;
import com.example.demospringboot.security.services.UserDetailsImpl;
import com.example.demospringboot.service.interfaces.EmployeeService;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.*;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        List<EmployeeDTO> employeeDtos = modelMapper.map(employees,
                new TypeToken<List<EmployeeDTO>>(){}.getType());
        return employeeDtos;
    }

    @Override
    public EmployeeDTO getEmployeeById(Integer id) {
        Optional<Employee> employeeData = employeeRepository.findById(id);
        EmployeeDTO employeeDtos = modelMapper.map(employeeData,
                EmployeeDTO.class);
        return employeeDtos;
    }

    @Override
    public String createEmployee(Employee employee) {
        //Employee _employee = new Employee(employee.getName());
        Role defaultRole = roleRepository.getByName("Intern");
        employee.setRole(defaultRole);
        employeeRepository.save(employee);
        return "Success";
    }

    @Override
    public String updateEmployee(Integer id, Update update) {
        Employee employeeData = employeeRepository.findById(id).get();
        employeeData.setName(update.getName());
        Integer roleId = update.getRoleId();
        Role roleUpdate = roleRepository.findById(roleId).get();
        employeeData.setRole(roleUpdate);
        Integer projectId = update.getProjectId();
        Project projectUpdate = projectRepository.findById(projectId).get();
        employeeData.setProject(projectUpdate);
        employeeRepository.save(employeeData);
        return "Success";
    }

    @Override
    public String deleteEmployee(Integer id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return "Success";
        } else {
            return "Failed";
        }
    }

    @Override
    public String deleteAllEmployee() {
        employeeRepository.deleteAll();
        return "Success";
    }

    @Override
    public List<EmployeeDTO> getByRoleId(Integer id) {
        List<Employee> employees = employeeRepository.getByRoleId(id);
        List<EmployeeDTO> employeeDtos = modelMapper.map(employees,
                new TypeToken<List<EmployeeDTO>>(){}.getType());
        return employeeDtos;
    }

    @Override
    public List<EmployeeDTO> getByRoleName(String str) {
        List<Employee> employees = employeeRepository.getByRoleName(str);
        List<EmployeeDTO> employeeDtos = modelMapper.map(employees,
                new TypeToken<List<EmployeeDTO>>(){}.getType());
        return employeeDtos;
    }

    @Override
    public List<EmployeeDTO> getByProjectId(Integer id) {
        List<Employee> employees = employeeRepository.getByProjectId(id);
        List<EmployeeDTO> employeeDtos = modelMapper.map(employees,
                new TypeToken<List<EmployeeDTO>>(){}.getType());
        return employeeDtos;
    }

    @Override
    public List<EmployeeDTO> getByProjectName(String str) {
        List<Employee> employees = employeeRepository.getByProjectName(str);
        List<EmployeeDTO> employeeDtos = modelMapper.map(employees,
                new TypeToken<List<EmployeeDTO>>(){}.getType());
        return employeeDtos;
    }

    @Override
    public List<EmployeeDTO> getEmployeeByName(String str) {
        List<Employee> employees = employeeRepository.getEmployeeByName(str);
        List<EmployeeDTO> employeeDtos = modelMapper.map(employees,
                new TypeToken<List<EmployeeDTO>>(){}.getType());
        return employeeDtos;
    }

    @Override
    public UserDetails loadUserByUsername(String Username) throws UsernameNotFoundException {
        Optional<Employee> employee = employeeRepository.findEmployeeByUsername(Username);
        if (!employee.isPresent()) {
            System.out.println("User not found!" + Username);
            throw new UsernameNotFoundException("User" + Username + "was not found in the database");
        }
        System.out.println("Found user! " + Username);
        System.out.println("Password " + employee.get().getPassword());
        System.out.println("Role " + getRole(employee.get()));
        UserDetails userDetails = new UserDetailsImpl(employee.get().getUsername(), employee.get().getPassword(), getRole(employee.get()));
        System.out.println(userDetails);
        return userDetails;
    }

    public Collection<SimpleGrantedAuthority> getRole(Employee employee) {
        Role role = employee.getRole();
        List<SimpleGrantedAuthority> auth = new ArrayList<>();
        auth.add(new SimpleGrantedAuthority(role.getName()));
        return auth;
    }

    @Override
    public Page<Employee> getAllEmployeeWithPaging(Pageable pageable){
        return employeeRepository.getAllEmployeeWithPaging(pageable);

    }
    @Override
    public Slice<Employee> getAllEmployeeWithSlice(Pageable pageable){
        return employeeRepository.getAllEmployeeWithSlice(pageable);
    }

    @Override
    public List<CountEmployeeRole> countTotalEmployeeByRole(){
        return employeeRepository.countTotalEmployeeByRole();
    }
    @Override
    public List<ICountEmployeeRole> countTotalEmployeeByRoleInterface(){
        return employeeRepository.countTotalEmployeeByRoleInterface();
    }
    @Override
    public List<CountEmployeeByRoleName> getEmployeeByRoleName(){
        return employeeRepository.getEmployeeByRoleName();
    }
//    @Override
//    public Flux<Result> getUserNcc() {
//        WebClient webClient = WebClient.create("http://hrm-api.nccsoft.vn");
//        Flux<Result> users = webClient.get()
//                .uri("/api/service/app/CheckIn/GetUserForCheckIn")
//                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .retrieve()
//                .bodyToFlux(Result.class);
//        return users;
//
//    }
//
//    public ResultList getNccUsers() throws JsonProcessingException {
//        RestTemplate restTemplate = new RestTemplate();
//        String requestURL = "http://hrm-api.nccsoft.vn/api/services/app/CheckIn/GetUserForCheckIn";
//
//        ResponseEntity<String> response = restTemplate.getForEntity(requestURL, String.class);
//        ObjectMapper objectMapper =  new ObjectMapper();
//
//        ResultList resultList = objectMapper.readValue(response.getBody(), ResultList.class);
//        System.out.println(resultList.getResults());
//        return resultList;
//    }

//    @Bean
//    CommandLineRunner run() {
//        return args -> {
//            Page<Employee> page = employeeRepository.findAll(PageRequest.of(0, 5));
//            System.out.println("in ra 5 user dau tien: ");
//            page.forEach(System.out::println);
//            page = employeeRepository.findAll(page.nextPageable());
//            System.out.println("in ra 5 user tiep theo: ");
//            page.forEach(System.out::println);
//            System.out.println("In ra so luong user o page hien tai: " + page.getSize());
//            System.out.println("In ra tong so luong user: " + page.getTotalElements());
//            System.out.println("In ra tong so page: " + page.getTotalPages());
//
//            page = employeeRepository.findAll(PageRequest.of(1, 5, Sort.by("name").descending()));
//            System.out.println("In ra 5 user page 1, sx theo desc: ");
//            page.forEach(System.out::println);
//
//            List<Employee> list = employeeRepository.findAllByNameLike("name-%", (Pageable) PageRequest.of(0, 5));
//            System.out.println(list);

//        };
//    }




}
