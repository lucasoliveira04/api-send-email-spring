package com.apisendemail.apisendemail.controller;

import com.apisendemail.apisendemail.dto.RequestMessage;
import com.apisendemail.apisendemail.service.SendMessageService;
import com.apisendemail.apisendemail.service.messages.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/")
@CrossOrigin(origins = "*")
public class IndexControllerV2 {

    private final SendMessageService sendMessageService;

    public IndexControllerV2(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
    }

    @PostMapping("/sendMessage")
    public ResponseEntity<String> send(@RequestBody RequestMessage request) throws MessagingException {
        sendMessageService.sendMessage(request, "v2");
        return ResponseEntity.ok("Email enviado com sucesso");
    }
}
