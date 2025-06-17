package com.operators;

import com.loggerutilities.LoggerUtility;
import java.util.Scanner;
import java.util.logging.Logger;

public class bitwise {
    private static final Logger logger = LoggerUtility.getLogger();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        logger.info("Bitwise program started");

        System.out.print("Enter first number (a): ");
        int a = scanner.nextInt();
        logger.info("User entered a = " + a);

        System.out.print("Enter second number (b): ");
        int b = scanner.nextInt();
        logger.info("User entered b = " + b);

        int andResult = a & b;
        int orResult = a | b;
        int xorResult = a ^ b;
        int notAResult = ~a;

        System.out.println("a & b: " + andResult);
        logger.info("a & b = " + andResult);

        System.out.println("a | b: " + orResult);
        logger.info("a | b = " + orResult);

        System.out.println("a ^ b: " + xorResult);
        logger.info("a ^ b = " + xorResult);

        System.out.println("~a: " + notAResult);
        logger.info("~a = " + notAResult);

        logger.info("Bitwise program completed");
        scanner.close();
    }
}
