package com.usermodule.emailverificationutil.service;

import com.usermodule.exceptionutil.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Slf4j
public class EmailService implements EmailSender{

    @Autowired
    private JavaMailSender mailSender;

    @Value("${email}")
    private String emailfrom;

    @Override
    @Async
    public void send(String to, String email) {

        try{
            MimeMessage mimeMessage= mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage,"utf-8");
            helper.setText(email,true);
            helper.setTo(to);
            helper.setSubject("Confirm Your Mail");
            helper.setFrom(emailfrom);
            mailSender.send(mimeMessage);
        }
        catch(MessagingException e){
            log.error("Fail to Send Email",e);
            throw new CustomException("Failed to send Email", HttpStatus.BAD_GATEWAY);
        }
    }
}
