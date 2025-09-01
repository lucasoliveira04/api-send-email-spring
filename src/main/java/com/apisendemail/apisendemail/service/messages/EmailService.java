package com.apisendemail.apisendemail.service.messages;

import com.apisendemail.apisendemail.dto.RequestMessage;
import jakarta.mail.MessagingException;

public interface EmailService {
    void sendMessage(RequestMessage requestMessage) throws MessagingException;
}
