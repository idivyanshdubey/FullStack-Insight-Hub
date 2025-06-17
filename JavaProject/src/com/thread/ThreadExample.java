package com.thread;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.*;

class MyThread extends Thread {
    private static final Logger logger = Logger.getLogger(MyThread.class.getName());

    @Override
    public void run() {
        logger.info("Thread is running.");
        System.out.println("Thread is running.");
    }
}

public class ThreadExample {
    private static final Logger logger = Logger.getLogger(ThreadExample.class.getName());

    public static void main(String[] args) {
        setupLogger();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a message to log: ");
        String userMessage = scanner.nextLine();
        logger.info("User input: " + userMessage);
        scanner.close();

        MyThread thread = new MyThread();
        logger.info("Starting thread...");
        thread.start();
    }

    private static void setupLogger() {
        try {
            LogManager.getLogManager().reset();
            FileHandler fileHandler = new FileHandler("core_java_programs.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);
            logger.setLevel(Level.INFO);

            // Also configure MyThread's logger
            Logger threadLogger = Logger.getLogger(MyThread.class.getName());
            threadLogger.addHandler(fileHandler);
            threadLogger.setUseParentHandlers(false);
            threadLogger.setLevel(Level.INFO);
        } catch (IOException e) {
            System.err.println("Failed to set up logger: " + e.getMessage());
        }
    }
}
