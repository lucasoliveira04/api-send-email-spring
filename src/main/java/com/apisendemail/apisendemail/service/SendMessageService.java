package com.apisendemail.apisendemail.service;

import com.apisendemail.apisendemail.dto.RequestMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendMessageService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    public String toEmail;

    public void sendMessage(RequestMessage requestMessage) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(requestMessage.fromEmail());
        message.setTo(toEmail);
        message.setSubject(requestMessage.subject());
        message.setText(requestMessage.body());

        mailSender.send(message);
    }
}
