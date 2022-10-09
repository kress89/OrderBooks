package com.example.mappings.mappings.controller;

import com.example.mappings.mappings.requests.CreateBookRequest;
import com.example.mappings.mappings.requests.CreateNotificationRequest;
import com.example.mappings.mappings.service.NotificationService;
import com.example.mappings.mappings.utils.ResponseGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @Autowired
    ResponseGenerator responseGenerator;

    @PostMapping(value = "/v3/notifications", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createNotification(@Valid @RequestBody CreateNotificationRequest notificationRequest) {
        log.info("Request Received {} ", notificationRequest);
        notificationService.sendNotification(notificationRequest.getSource());
        return responseGenerator.generateResponse("Notification was send to orders!", HttpStatus.CREATED);
    }
}
