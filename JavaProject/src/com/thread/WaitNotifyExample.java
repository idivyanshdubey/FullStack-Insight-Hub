package com.thread;

import com.loggerutilities.LoggerUtility;
import java.util.Scanner;
import java.util.logging.Logger;

class SharedResource {
    private static final Logger logger = LoggerUtility.getLogger();

    public synchronized void produce() throws InterruptedException {
        logger.info("Producer is producing...");
        System.out.println("Producer is producing...");
        wait(); // Releases lock and waits
        logger.info("Producer resumed.");
        System.out.println("Producer resumed.");
    }

    public synchronized void consume() {
        logger.info("Consumer is consuming...");
        System.out.println("Consumer is consuming...");
        notify(); // Wakes up the waiting thread
    }
}

public class WaitNotifyExample {
    private static final Logger logger = LoggerUtility.getLogger();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Run producer first? (yes/no): ");
        String choice = scanner.nextLine().trim().toLowerCase();
        logger.info("User input: " + choice);
        scanner.close();

        SharedResource resource = new SharedResource();

        Thread producer = new Thread(() -> {
            try {
                resource.produce();
            } catch (InterruptedException e) {
                logger.severe("Producer interrupted: " + e.getMessage());
            }
        });

        Thread consumer = new Thread(resource::consume);

        if (choice.equals("yes")) {
            producer.start();
            consumer.start();
        } else {
            consumer.start();
            producer.start();
        }
    }
}
