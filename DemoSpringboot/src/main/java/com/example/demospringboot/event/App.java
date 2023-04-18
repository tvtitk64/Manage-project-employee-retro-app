//package com.example.demospringboot.event;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Profile;
//
//
//@SpringBootApplication
//public class App {
//    @Autowired
//    MyHouse myHouse;
//
//    public static void main(String[] args) {
//        SpringApplication.run(App.class, args);
//    }
//
//    @Bean
//    CommandLineRunner run() {
//        return args -> {
//            System.out.println(Thread.currentThread().getName() + ": Loda di toi cua nha");
//            System.out.println(Thread.currentThread().getName() + ": => Loda bam chuong va khai bao ho ten");
//            myHouse.rangDoorbellBy("Loda");
//            System.out.println(Thread.currentThread().getName() + ": Loda quay lung bo di");
//
//        };
//    }
//}
