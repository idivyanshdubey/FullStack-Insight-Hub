package com.loops;

import com.loggerutilities.LoggerUtility;
import java.util.Scanner;
import java.util.logging.Logger;

public class Fors {
    private static final Logger logger = LoggerUtility.getLogger();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        logger.info("Fors program started.");

        try {
            System.out.print("Enter start number: ");
            int start = scanner.nextInt();

            System.out.print("Enter end number: ");
            int end = scanner.nextInt();

            for (int i = start; i <= end; i++) {
                logger.info("Number: " + i);
            }

        } catch (Exception e) {
            logger.severe("Error occurred: " + e.getMessage());
        } finally {
            scanner.close();
            logger.info("Fors program ended.");
        }
    }
}
