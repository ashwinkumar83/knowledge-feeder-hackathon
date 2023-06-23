package com.ashzone.hackathon.knowledgefeeder.controller;

import com.ashzone.hackathon.knowledgefeeder.scheduler.KnowledgeFeederScheduler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/* This is to trigger the Mail to users via API */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/email")
public class MailSenderController {

    private final KnowledgeFeederScheduler scheduler;
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/send")
    public ResponseEntity<Object> sendEmail() {
        scheduler.sendEmails();
        return  ResponseEntity.status(HttpStatus.OK).build();
    }
}
