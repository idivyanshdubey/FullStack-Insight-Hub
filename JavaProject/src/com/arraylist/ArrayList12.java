package com.arraylist;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.*;

public class ArrayList12 {
    private static final Logger logger = Logger.getLogger(ArrayList12.class.getName());

    static {
        setupLogger();
    }

    public static void main(String[] args) {
        logger.info("Program started.");
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> fruits = new ArrayList<>();

        System.out.print("Enter the number of fruits to add: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // consume newline

        for (int i = 0; i < n; i++) {
            System.out.print("Enter fruit " + (i + 1) + ": ");
            String fruit = scanner.nextLine();
            fruits.add(fruit);
            logger.info("Added fruit: " + fruit);
        }

        System.out.println("Fruit at index 1: " + (fruits.size() > 1 ? fruits.get(1) : "Not available"));
        if (fruits.size() > 0) {
            logger.info("Removing fruit at index 0: " + fruits.get(0));
            fruits.remove(0);
        }

        System.out.println("Updated list: " + fruits);
        logger.info("Updated list: " + fruits);

        System.out.println("List size: " + fruits.size());
        for (String f : fruits) {
            System.out.println(f);
        }

        if (fruits.size() > 1) {
            System.out.println("Using get: " + fruits.get(1));
        } else {
            System.out.println("Not enough elements to get index 1.");
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
