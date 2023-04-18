package com.example.demospringboot.resttemplateandwebclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/webclient/client_ncc")
public class WebClientController {
    @Autowired
    private WebClientService webClientService;

    @GetMapping
    public Mono<ResultList> getAllEmployees() {
//        System.out.println(webClientService.getAll());
        return webClientService.resttemplatewebclient();
    }
}
