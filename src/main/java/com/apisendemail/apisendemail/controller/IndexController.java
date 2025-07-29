package com.apisendemail.apisendemail.controller;

import com.apisendemail.apisendemail.dto.RequestMessage;
import com.apisendemail.apisendemail.service.SendMessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class IndexController {

    private final SendMessageService sendMessageService;

    public IndexController(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
    }

    @PostMapping("/sendMessage")
    public ResponseEntity<String> send(@RequestBody RequestMessage request){
        sendMessageService.sendMessage(request);

        return ResponseEntity.ok("Email enviado com sucesso");
    }
}
