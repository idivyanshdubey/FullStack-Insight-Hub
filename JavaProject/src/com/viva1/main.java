package com.viva1;

import java.util.logging.Logger;
import com.loggerutilities.LoggerUtility;

public class main {
    private static final Logger logger = LoggerUtility.getLogger();

    public static void main(String[] args) {
        logger.info("Program 'main' started.");

        Variables v = new Variables();
        logger.info("Variables object created.");

        v.display();
        logger.info("Called v.display().");

        logger.info("Accessing static variable: " + Variables.sharedVar);
        System.out.println(Variables.sharedVar);

        logger.info("Accessing instance variable: " + v.instanceVar);
        System.out.println(v.instanceVar);

        logger.info("Calling getInstance(): " + v.getInsatance());
        System.out.println(v.getInsatance());

        logger.info("Program 'main' ended.");
    }
}
