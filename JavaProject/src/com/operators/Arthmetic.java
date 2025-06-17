package com.operators;

import com.loggerutilities.LoggerUtility;
import java.util.Scanner;
import java.util.logging.Logger;

public class Arthmetic {
    private static final Logger logger = LoggerUtility.getLogger();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        logger.info("Program started");

        System.out.print("Enter first number: ");
        int a = scanner.nextInt();
        logger.info("First number entered: " + a);

        System.out.print("Enter second number: ");
        int b = scanner.nextInt();
        logger.info("Second number entered: " + b);

        System.out.println("Addition: " + (a + b));
        logger.info("Addition: " + (a + b));

        System.out.println("Subtraction: " + (a - b));
        logger.info("Subtraction: " + (a - b));

        System.out.println("Multiplication: " + (a * b));
        logger.info("Multiplication: " + (a * b));

        if (b != 0) {
            System.out.println("Division: " + (a / b));
            logger.info("Division: " + (a / b));

            System.out.println("Modulo: " + (a % b));
            logger.info("Modulo: " + (a % b));
        } else {
            System.out.println("Division and Modulo by zero are not allowed.");
            logger.warning("Attempted division or modulo by zero.");
        }

        logger.info("Program completed");
        scanner.close();
    }
}
