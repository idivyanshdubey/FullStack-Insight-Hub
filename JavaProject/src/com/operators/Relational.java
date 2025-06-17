package com.operators;

import com.loggerutilities.LoggerUtility;
import java.util.Scanner;
import java.util.logging.Logger;

public class Relational {
    private static final Logger logger = LoggerUtility.getLogger();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        logger.info("Relational program started");

        System.out.print("Enter first number (a): ");
        int a = scanner.nextInt();
        logger.info("User entered a = " + a);

        System.out.print("Enter second number (b): ");
        int b = scanner.nextInt();
        logger.info("User entered b = " + b);

        System.out.println("a < b: " + (a < b));
        logger.info("a < b = " + (a < b));

        System.out.println("a > b: " + (a > b));
        logger.info("a > b = " + (a > b));

        System.out.println("a == b: " + (a == b));
        logger.info("a == b = " + (a == b));

        System.out.println("a != b: " + (a != b));
        logger.info("a != b = " + (a != b));

        System.out.println("a <= b: " + (a <= b));
        logger.info("a <= b = " + (a <= b));

        System.out.println("a >= b: " + (a >= b));
        logger.info("a >= b = " + (a >= b));

        logger.info("Relational program completed");
        scanner.close();
    }
}
