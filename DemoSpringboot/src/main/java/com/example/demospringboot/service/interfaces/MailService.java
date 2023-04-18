package com.example.demospringboot.service.interfaces;

import com.example.demospringboot.dto.DataMailDTO;

import javax.mail.MessagingException;

public interface MailService {
    void sendHtmlMail(DataMailDTO dataMail) throws MessagingException;
}
