package com.polymorphism;

import com.loggerutilities.LoggerUtility;
import java.util.logging.Logger;

class Main {
    private static final Logger logger = LoggerUtility.getLogger();

    public static void main(String[] args) {
        logger.info("Main method started");

        Polymorphiosm ph = new Polymorphiosm();

        int result = ph.add(12, 12);
        int result1 = ph.add(12, 12, 12);

        logger.info("Result of add(12, 12): " + result);
        logger.info("Result of add(12, 12, 12): " + result1);

        System.out.println("result: " + result);
        System.out.println("result1: " + result1);

        logger.info("Main method completed");
    }
}
