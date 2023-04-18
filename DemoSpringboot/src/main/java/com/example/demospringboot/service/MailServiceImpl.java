package com.example.demospringboot.service;

import com.example.demospringboot.dto.DataMailDTO;
import com.example.demospringboot.service.interfaces.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import java.io.File;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    JavaMailSender mailSender;

    @Override
    @Async
    public void sendHtmlMail(DataMailDTO dataMail) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        String htmlMsg = "<h3> Im testing send a HTML email</h3>"
                + "<img src='https://ncc.asia/images/logo/logo.png'>";
        message.setContent(htmlMsg, "text/html");

        FileSystemResource file = new FileSystemResource(new File("C:\\Users\\toant\\Downloads\\test.txt"));
        helper.addAttachment("Demo Mail", file);
        helper.setTo(dataMail.getTo());
        helper.setSubject(dataMail.getSubject());
        helper.setFrom("tranvutoan1412@gmail.com");
        mailSender.send(message);
    }
}
