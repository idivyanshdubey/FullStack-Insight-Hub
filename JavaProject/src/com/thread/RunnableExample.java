package com.thread;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.*;

class MyRunnable implements Runnable {
    private static final Logger logger = Logger.getLogger(MyRunnable.class.getName());

    @Override
    public void run() {
        logger.info("Runnable thread is running.");
        System.out.println("Runnable thread is running.");
    }
}

public class RunnableExample {
    private static final Logger logger = Logger.getLogger(RunnableExample.class.getName());

    public static void main(String[] args) {
        setupLogger();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a message to log: ");
        String userMessage = scanner.nextLine();
        logger.info("User input: " + userMessage);
        scanner.close();

        MyRunnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable);
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

            // Also configure MyRunnable's logger
            Logger runnableLogger = Logger.getLogger(MyRunnable.class.getName());
            runnableLogger.addHandler(fileHandler);
            runnableLogger.setUseParentHandlers(false);
            runnableLogger.setLevel(Level.INFO);
        } catch (IOException e) {
            System.err.println("Failed to set up logger: " + e.getMessage());
        }
    }
}
