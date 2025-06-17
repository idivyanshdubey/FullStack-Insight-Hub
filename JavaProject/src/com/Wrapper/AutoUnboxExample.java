package com.Wrapper;

import com.loggerutilities.LoggerUtility;
import java.util.logging.Logger;

public class AutoUnboxExample {
    private static final Logger logger = LoggerUtility.getLogger();

    public static void main(String[] args) {
        logger.info("Program AutoUnboxExample started.");

        // Autoboxing: Primitive to Wrapper
        Integer boxedInt = 42;
        logger.info("Autoboxed value: " + boxedInt);
        System.out.println("Autoboxed value: " + boxedInt);

        // Unboxing: Wrapper to Primitive
        int primitiveInt = boxedInt;
        logger.info("Unboxed value: " + primitiveInt);
        System.out.println("Unboxed value: " + primitiveInt);

        // Using autoboxing and unboxing in calculations
        Integer num1 = 10;
        Integer num2 = 20;
        int sum = num1 + num2; // Unboxing happens automatically
        logger.info("Sum of num1 and num2: " + sum);
        System.out.println("Sum: " + sum);

        logger.info("Program AutoUnboxExample ended.");
    }
}
