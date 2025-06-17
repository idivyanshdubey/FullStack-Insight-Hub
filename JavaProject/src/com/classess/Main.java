package com.classess;

import java.util.Scanner;
import java.util.logging.*;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    static {
        setupLogger();
    }

    public static void main(String[] args) {
        logger.info("Program started.");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the color of the car: ");
        String color = scanner.nextLine();

        Opps myCar = new Opps(); // Create an object
        myCar.color = color;     // Set property
        logger.info("Car object created with color: " + color);

        myCar.display();         // Call method
        logger.info("display() method called.");

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
