package com.apisendemail.apisendemail.service.messages;

import com.apisendemail.apisendemail.dto.RequestMessage;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@Qualifier("v1")
public class SimpleEmailServiceImpl implements EmailService{

    private final JavaMailSender mailSender;

    public SimpleEmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Value("${spring.mail.username}")
    private String toEmail;

    @Override
    public void sendMessage(RequestMessage requestMessage) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setFrom(requestMessage.fromEmail());
        helper.setTo(toEmail);
        helper.setSubject(requestMessage.subject());
        helper.setText(requestMessage.body(), true);
        mailSender.send(mimeMessage);
    }
}
