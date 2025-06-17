package com.Wrapper;

import java.util.logging.Logger;
import com.loggerutilities.LoggerUtility;

public class ConversionExample {
    private static final Logger logger = LoggerUtility.getLogger();

    public static void main(String[] args) {
        logger.info("Program ConversionExample started.");

        // String to primitive
        int num = Integer.parseInt("123");
        logger.info("Converted String to int: " + num);
        System.out.println("String to int: " + num);

        // Primitive to wrapper
        Float floatNum = Float.valueOf(num);
        logger.info("Converted int to Float wrapper: " + floatNum);
        System.out.println("Primitive to wrapper: " + floatNum);

        // Wrapper to string
        String str = floatNum.toString();
        logger.info("Converted Float wrapper to String: " + str);
        System.out.println("Wrapper to string: " + str);

        // Wrapper to primitive
        float primitiveFloat = floatNum.floatValue();
        logger.info("Converted Float wrapper to primitive float: " + primitiveFloat);
        System.out.println("Wrapper to primitive: " + primitiveFloat);

        logger.info("Program ConversionExample ended.");
    }
}
