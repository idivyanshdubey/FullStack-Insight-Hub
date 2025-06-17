package com.classess;

import java.util.Scanner;
import java.util.logging.*;

class Example {
    int value;

    void displayValue() {
        System.out.println("Value: " + value);
    }
}

public class MultipleObjectReference {
    private static final Logger logger = Logger.getLogger(MultipleObjectReference.class.getName());

    static {
        setupLogger();
    }

    public static void main(String[] args) {
        logger.info("Program started.");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a value for obj1: ");
        int inputValue = scanner.nextInt();

        Example obj1 = new Example(); // First reference variable
        obj1.value = inputValue;
        logger.info("obj1 created and value set to: " + inputValue);

        Example obj2 = obj1; // Second reference variable pointing to the same object
        System.out.print("Enter a new value to modify through obj2: ");
        int modifiedValue = scanner.nextInt();
        obj2.value = modifiedValue;
        logger.info("obj2 modified value to: " + modifiedValue);

        System.out.println("Accessing through obj1:");
        obj1.displayValue();
        System.out.println("Accessing through obj2:");
        obj2.displayValue();

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
