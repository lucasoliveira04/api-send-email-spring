package com.apisendemail.apisendemail.service;

import com.apisendemail.apisendemail.dto.RequestMessage;
import com.apisendemail.apisendemail.service.messages.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SendMessageService {
    private final Map<String, EmailService> senders;

    public SendMessageService(
            @Qualifier("v1") EmailService emailServiceV1,
            @Qualifier("v2") EmailService emailServiceV2
    ) {
        this.senders = Map.of(
                "v1", emailServiceV1,
                "v2", emailServiceV2
        );
    }

    public void sendMessage(RequestMessage requestMessage, String version) throws MessagingException {
        EmailService emailService = senders.get(version);
        if (emailService == null) {
            throw new MessagingException("Version not found");
        }
        emailService.sendMessage(requestMessage);
    }
}
