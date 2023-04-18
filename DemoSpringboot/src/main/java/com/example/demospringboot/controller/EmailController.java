package com.example.demospringboot.controller;

import com.example.demospringboot.dto.DataMailDTO;
import com.example.demospringboot.service.interfaces.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
public class EmailController {
    @Autowired
    private MailService mailService;

    @PostMapping("/sendMail")
    public String sendMail(@RequestBody DataMailDTO dataMailDTO) throws MessagingException {
//        System.out.println(dataMailDTO);
        mailService.sendHtmlMail(dataMailDTO);
        return "Email Sent Succesfully!";
    }

}
