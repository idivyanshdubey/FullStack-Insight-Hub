package com.trycatchfinally;

import com.loggerutilities.LoggerUtility;
import java.util.Scanner;
import java.util.logging.Logger;

public class Cons {
    private static final Logger logger = LoggerUtility.getLogger();

    public static void main(String[] args) {
        logger.info("Cons program started.");
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter a number to divide 100 by: ");
            int divisor = scanner.nextInt();

            int result = 100 / divisor;
            System.out.println("Result: " + result);
            logger.info("Division successful. Result: " + result);

        } catch (ArithmeticException e) {
            logger.severe("ArithmeticException: Division by zero is not allowed.");
        } catch (Exception e) {
            logger.severe("Exception occurred: " + e.getMessage());
        } finally {
            scanner.close();
            logger.info("Cons program ended.");
        }
    }
}
