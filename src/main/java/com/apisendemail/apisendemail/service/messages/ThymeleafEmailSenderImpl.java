package com.apisendemail.apisendemail.service.messages;

import com.apisendemail.apisendemail.dto.RequestMessage;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
@Qualifier("v2")
public class ThymeleafEmailSenderImpl implements EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    public String toEmail;

    @Override
    public void sendMessage(RequestMessage requestMessage) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(
                mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name()
        );

        Context context = new Context();
        context.setVariable("contato", requestMessage.fromEmail());
        context.setVariable("email", requestMessage.fromEmail());
        context.setVariable("feedback", requestMessage.subject());

        String html = templateEngine.process("email-template", context);

        helper.setFrom(requestMessage.fromEmail());
        helper.setTo(toEmail);
        helper.setSubject(requestMessage.subject());
        helper.setText(html, true);

        mailSender.send(mimeMessage);
    }
}
