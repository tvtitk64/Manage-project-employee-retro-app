package com.example.demospringboot.resttemplateandwebclient;

import com.example.demospringboot.dto.EmployeeDTO;
import com.example.demospringboot.entity.Employee;
import com.example.demospringboot.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class WebClientService {
    @Autowired
    WebClient webClient;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

//    public List<Result> getAll() {
//        Mono<ResultList> objects = webClient.get().uri("")
//                .retrieve()
//                .bodyToMono(ResultList.class);
//        ResultList resultList = objects.block();
//        return resultList.getResult();
//    }

    public Mono<ResultList> resttemplatewebclient() {
        WebClient webClient = WebClient.create("http://hrm-api.nccsoft.vn"); //create instance to host
        Mono<ResultList> users = webClient.get()
                .uri("/api/services/app/CheckIn/GetUserForCheckIn")
//                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(ResultList.class);
        return users;

    }


//    @Transactional
//    public List<EmployeeDTO> syncData() {
//        List<Result> results = getAll();
//        List<Employee> employees = new ArrayList<>();
//        for (Result result : results) {
//            Employee employee = new Employee();
//            employee.setName(result.getLastName() + " " + result.getFirstName());
//            employee.setEmail(result.getEmail());
//            employees.add(employee);
//        }
//
//        for (Employee employee : employees) {
//            boolean isExists = employeeRepository.e
//        }
//    }

}
