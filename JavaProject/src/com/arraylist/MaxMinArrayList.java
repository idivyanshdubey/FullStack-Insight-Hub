package com.arraylist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.*;

public class MaxMinArrayList {
    private static final Logger logger = Logger.getLogger(MaxMinArrayList.class.getName());

    static {
        setupLogger();
    }

    public static void main(String[] args) {
        logger.info("Program started.");
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();

        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();

        System.out.println("Enter " + n + " integers:");
        for (int i = 0; i < n; i++) {
            int value = scanner.nextInt();
            list.add(value);
            logger.info("Added value: " + value);
        }

        if (!list.isEmpty()) {
            int max = Collections.max(list);
            int min = Collections.min(list);

            System.out.println("Maximum value: " + max);
            System.out.println("Minimum value: " + min);

            logger.info("Maximum value: " + max);
            logger.info("Minimum value: " + min);
        } else {
            System.out.println("The list is empty.");
            logger.warning("Attempted to find max/min on an empty list.");
        }

        scanner.close();
    }

    private static void setupLogger() {
        try {
            LogManager.getLogManager().reset();
            FileHandler fh = new FileHandler("core_java_programs.log", true);
            fh.setFormatter(new SimpleFormatter());
            Logger rootLogger = Logger.getLogger("");
            rootLogger.addHandler(fh);
            rootLogger.setLevel(Level.INFO);
        } catch (Exception e) {
            System.err.println("Logger setup failed: " + e.getMessage());
        }
    }
}
