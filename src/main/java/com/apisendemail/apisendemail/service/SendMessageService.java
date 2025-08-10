package com.apisendemail.apisendemail.service;

import com.apisendemail.apisendemail.dto.RequestMessage;
import com.apisendemail.apisendemail.model.Mensagem;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendMessageService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    public String toEmail;

    public void sendMessage(RequestMessage requestMessage) throws MessagingException {
        String htmlContent = Mensagem.montarMensagem(
                requestMessage.fromEmail(),
                requestMessage.body()
        );

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        message.setFrom(requestMessage.fromEmail());
        message.setTo(toEmail);
        message.setSubject(requestMessage.subject());
        message.setText(htmlContent, true);

        mailSender.send(mimeMessage);
    }
}
