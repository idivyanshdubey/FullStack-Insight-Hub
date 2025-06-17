package com.thread;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.*;

class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

public class Exp {
    private static final Logger logger = Logger.getLogger(Exp.class.getName());

    static {
        try {
            // Create a FileHandler that writes log to a file named app.log
            FileHandler fileHandler = new FileHandler("core_java_programs.log", true); // true for append mode
            fileHandler.setFormatter(new SimpleFormatter()); // Optional: readable format
            logger.addHandler(fileHandler);
            logger.setLevel(Level.INFO); // Set desired log level
            logger.setUseParentHandlers(false); // Prevent logging to console
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to set up file handler", e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of increments per thread: ");
        int increments = scanner.nextInt();
        scanner.close();

        Counter counter = new Counter();

        Runnable task = () -> {
            logger.info(Thread.currentThread().getName() + " started.");
            for (int i = 0; i < increments; i++) {
                counter.increment();
            }
            logger.info(Thread.currentThread().getName() + " finished.");
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        logger.info("Starting threads...");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        logger.info("Threads completed.");

        System.out.println("Final count: " + counter.getCount());
    }
}
