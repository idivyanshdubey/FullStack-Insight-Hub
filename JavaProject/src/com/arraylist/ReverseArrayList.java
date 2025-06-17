package com.arraylist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.*;

public class ReverseArrayList {
    private static final Logger logger = Logger.getLogger(ReverseArrayList.class.getName());

    static {
        setupLogger();
    }

    public static void main(String[] args) {
        logger.info("Program started.");
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();

        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.println("Enter " + n + " strings:");
        for (int i = 0; i < n; i++) {
            String input = scanner.nextLine();
            list.add(input);
            logger.info("Added: " + input);
        }

        logger.info("Original list: " + list);
        Collections.reverse(list);
        logger.info("Reversed list: " + list);

        System.out.println("Reversed ArrayList: " + list);
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
