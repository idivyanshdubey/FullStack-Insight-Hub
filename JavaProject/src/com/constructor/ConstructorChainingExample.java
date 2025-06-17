package com.constructor;

import java.util.Scanner;
import java.util.logging.*;

class Car11 {
    private static final Logger logger = Logger.getLogger(Car11.class.getName());

    String model;
    int year;

    // First constructor
    Car11() {
        this("Unknown Model", 2000); // Calls second constructor
        System.out.println("Default constructor executed.");
        logger.info("Default constructor executed.");
    }

    // Second constructor
    Car11(String model, int year) {
        this.model = model;
        this.year = year;
        System.out.println("Parameterized constructor executed.");
        logger.info("Parameterized constructor executed with model: " + model + ", year: " + year);
    }

    void showDetails() {
        System.out.println("Car Model: " + model + ", Year: " + year);
        logger.info("showDetails() called. Model: " + model + ", Year: " + year);
    }
}

public class ConstructorChainingExample {
    private static final Logger logger = Logger.getLogger(ConstructorChainingExample.class.getName());

    static {
        setupLogger();
    }

    public static void main(String[] args) {
        logger.info("Program started.");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter car model (or leave blank for default): ");
        String model = scanner.nextLine();

        Car11 car;
        if (model.isBlank()) {
            car = new Car11(); // Calls default constructor
        } else {
            System.out.print("Enter car year: ");
            int year = scanner.nextInt();
            car = new Car11(model, year); // Calls parameterized constructor
        }

        car.showDetails();
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
