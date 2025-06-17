package com.thread;

import com.loggerutilities.LoggerUtility;
import java.util.Scanner;
import java.util.logging.Logger;

class RequestHandler extends Thread {
    private static final Logger logger = LoggerUtility.getLogger();
    private String requestName;

    public RequestHandler(String requestName) {
        this.requestName = requestName;
    }

    public void run() {
        logger.info("Processing request: " + requestName);
        System.out.println("Processing request: " + requestName);
        try {
            Thread.sleep(2000); // Simulate processing time
        } catch (InterruptedException e) {
            logger.severe("Error processing request '" + requestName + "': " + e.getMessage());
        }
        logger.info("Request processed: " + requestName);
        System.out.println("Request processed: " + requestName);
    }
}

public class WebServer {
    private static final Logger logger = LoggerUtility.getLogger();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter first request: ");
        String req1 = scanner.nextLine();
        logger.info("User input: " + req1);

        System.out.print("Enter second request: ");
        String req2 = scanner.nextLine();
        logger.info("User input: " + req2);

        System.out.print("Enter third request: ");
        String req3 = scanner.nextLine();
        logger.info("User input: " + req3);

        scanner.close();

        Thread request1 = new RequestHandler(req1);
        Thread request2 = new RequestHandler(req2);
        Thread request3 = new RequestHandler(req3);

        request1.start();
        request2.start();
        request3.start();
    }
}
