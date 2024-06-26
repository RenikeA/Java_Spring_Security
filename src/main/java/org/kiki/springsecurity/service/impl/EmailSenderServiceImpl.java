package org.kiki.springsecurity.service.impl;

import lombok.RequiredArgsConstructor;
import org.kiki.springsecurity.dto.EmailDetails;
import org.kiki.springsecurity.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;


    @Value("{spring.mail.username}")
    private String senderEmail;

    @Override
    public void sendEmaiAlert (EmailDetails emailDetails){

        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(senderEmail);
            simpleMailMessage.setTo(emailDetails.getRecipient());
            simpleMailMessage.setSubject(emailDetails.getSubject());
            simpleMailMessage.setText(emailDetails.getMessagebody());


            javaMailSender.send(simpleMailMessage);

            System.out.println("Mail sent successfully");

        }catch (MailException e){
            throw new RuntimeException("email not sent ");
        }

    }

}
