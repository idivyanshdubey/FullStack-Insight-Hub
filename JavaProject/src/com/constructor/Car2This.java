package com.constructor;

import java.util.Scanner;
import java.util.logging.*;

public class Car2This {
    private static final Logger logger = Logger.getLogger(Car2This.class.getName());

    String color;

    Car2This(String color) {
        this.color = color;
        logger.info("Constructor called with color: " + color);
    }

    void display() {
        System.out.println("Color: " + this.color);
        logger.info("display() method called. Color: " + this.color);
    }

    public static void main(String[] args) {
        setupLogger();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the color of the car: ");
        String inputColor = scanner.nextLine();

        Car2This car = new Car2This(inputColor);
        car.display();

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
