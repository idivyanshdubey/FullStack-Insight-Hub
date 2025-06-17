package com.singleton;

import com.loggerutilities.LoggerUtility;
import java.util.logging.Logger;

// Singleton class definition
class Singleton {
    // Logger instance from LoggerUtility
    private static final Logger logger = LoggerUtility.getLogger();

    // Step 2: Static variable to hold the single instance
    private static Singleton instance;

    // Step 1: Private constructor to prevent external instantiation
    private Singleton() {
        logger.info("Private constructor called");
    }

    // Step 3: Public static method to provide access to the instance
    public static Singleton getInstance() {
        if (instance == null) {
            // If no instance exists, create one
            logger.info("Creating new Singleton instance");
            instance = new Singleton();
        } else {
            // If instance already exists, return it
            logger.info("Returning existing Singleton instance");
        }
        return instance;
    }

    // Method to demonstrate functionality
    public void showMessage() {
        logger.info("Singleton instance method called");
        System.out.println("Singleton instance method called");
    }
}

// Main class to test Singleton behavior
public class Exmp {
    // Logger instance for the main class
    private static final Logger logger = LoggerUtility.getLogger();

    public static void main(String[] args) {
        logger.info("Main method started");

        // Step 4: Access the Singleton instance
        Singleton singleton1 = Singleton.getInstance();
        singleton1.showMessage();

        // Access the Singleton instance again
        Singleton singleton2 = Singleton.getInstance();
        singleton2.showMessage();

        // Verify that both references point to the same instance
        boolean sameInstance = singleton1 == singleton2;
        logger.info("Are both instances the same? " + sameInstance);
        System.out.println("Are both instances the same? " + sameInstance);
    }
}
