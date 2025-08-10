package com.apisendemail.apisendemail.controller;

import com.apisendemail.apisendemail.dto.RequestMessage;
import com.apisendemail.apisendemail.service.SendMessageService;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*")
public class IndexController {

    private final SendMessageService sendMessageService;

    public IndexController(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
    }

    @PostMapping("/sendMessage")
    public ResponseEntity<String> send(@RequestBody RequestMessage request) throws MessagingException {
        sendMessageService.sendMessage(request);

        return ResponseEntity.ok("Email enviado com sucesso");
    }
}
