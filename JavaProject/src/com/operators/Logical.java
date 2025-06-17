package com.operators;

import com.loggerutilities.LoggerUtility;
import java.util.Scanner;
import java.util.logging.Logger;

public class Logical {
    private static final Logger logger = LoggerUtility.getLogger();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        logger.info("Logical program started");

        System.out.print("Enter value for x (true/false): ");
        boolean x = scanner.nextBoolean();
        logger.info("User entered x = " + x);

        System.out.print("Enter value for y (true/false): ");
        boolean y = scanner.nextBoolean();
        logger.info("User entered y = " + y);

        boolean andResult = x && y;
        boolean orResult = x || y;
        boolean notXResult = !x;

        System.out.println("x && y: " + andResult);
        logger.info("x && y = " + andResult);

        System.out.println("x || y: " + orResult);
        logger.info("x || y = " + orResult);

        System.out.println("!x: " + notXResult);
        logger.info("!x = " + notXResult);

        logger.info("Logical program completed");
        scanner.close();
    }
}
