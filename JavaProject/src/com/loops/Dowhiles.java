package com.loops;

import java.util.Scanner;
import java.util.logging.*;

public class Dowhiles {
    private static final Logger logger = Logger.getLogger(Dowhiles.class.getName());

    static {
        try {
            LogManager.getLogManager().reset();

            // File Handler
            FileHandler fileHandler = new FileHandler("core_java_programs.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.INFO);

            // Console Handler
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new SimpleFormatter());
            consoleHandler.setLevel(Level.INFO);

            logger.addHandler(fileHandler);
            logger.addHandler(consoleHandler);
            logger.setLevel(Level.INFO);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Logging setup failed", e);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Menu:");
            System.out.println("1. Option A");
            System.out.println("2. Option B");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            logger.info("User selected option: " + choice);

            switch (choice) {
                case 1:
                    logger.info("Executing Option A");
                    break;
                case 2:
                    logger.info("Executing Option B");
                    break;
                case 3:
                    logger.info("User chose to exit.");
                    break;
                default:
                    logger.warning("Invalid choice entered: " + choice);
            }

        } while (choice != 3);

        System.out.println("Exiting...");
        scanner.close();
    }
}
