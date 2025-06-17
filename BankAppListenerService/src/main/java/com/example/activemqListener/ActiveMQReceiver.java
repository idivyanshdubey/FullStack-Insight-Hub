package com.example.activemqListener;

import org.springframework.jms.annotation.JmsListener;

import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ActiveMQReceiver {
    private static final Logger logger = LoggerFactory.getLogger(ActiveMQReceiver.class);

    @JmsListener(destination = "bankQueue", containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(String message) {
        try {
            logger.info("Received message: {}", message);
            // Process the message (e.g., store in DB, trigger business logic)
        } catch (Exception e) {
            logger.error("Error processing message: {}", message, e);
        }
    }
}