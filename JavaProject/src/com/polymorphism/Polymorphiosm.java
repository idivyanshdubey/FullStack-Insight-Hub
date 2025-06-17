package com.polymorphism;

import com.loggerutilities.LoggerUtility;
import java.util.logging.Logger;

public class Polymorphiosm {
    private static final Logger logger = LoggerUtility.getLogger();

    // Method overloading: two parameters
    public int add(int a, int b) {
        int result = a + b;
        logger.info("add(int, int) called with values: " + a + ", " + b + " | Result: " + result);
        return result;
    }

    // Method overloading: three parameters
    public int add(int a, int b, int c) {
        int result = a + b + c;
        logger.info("add(int, int, int) called with values: " + a + ", " + b + ", " + c + " | Result: " + result);
        return result;
    }
}
