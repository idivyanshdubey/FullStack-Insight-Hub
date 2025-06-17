package com.Wrapper;

import java.util.ArrayList;
import java.util.logging.Logger;
import com.loggerutilities.LoggerUtility;

public class WrapperExample {
    private static final Logger logger = LoggerUtility.getLogger();

    public static void main(String[] args) {
        logger.info("Program WrapperExample started.");

        // Using wrapper classes in a collection
        ArrayList<Integer> numbers = new ArrayList<>();
        logger.info("Created ArrayList for Integer.");

        numbers.add(10); // Autoboxing happens automatically
        logger.info("Added 10 to ArrayList (autoboxed).");

        numbers.add(20);
        logger.info("Added 20 to ArrayList (autoboxed).");

        System.out.println(numbers);
        logger.info("Printed ArrayList: " + numbers);

        logger.info("Program WrapperExample ended.");
    }
}
