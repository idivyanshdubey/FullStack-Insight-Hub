package com.bankingApp.j.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bankingApp.j.service.KafkaProducerService;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    private final KafkaProducerService producerService;

    public KafkaController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    @RequestMapping(value = "/publish", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<String> publish(@RequestParam String message) {
        producerService.sendMessage("test-topic", message);
        return ResponseEntity.ok("Message sent to Kafka");
    }
}
