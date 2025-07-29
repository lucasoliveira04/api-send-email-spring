package com.apisendemail.apisendemail.dto;

public record RequestMessage(String fromEmail, String subject, String body) {
}
