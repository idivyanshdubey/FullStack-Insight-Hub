package com.trycatchfinally; //package

import com.loggerutilities.LoggerUtility; //import logger utility
import java.util.logging.Logger;
import java.util.logging.Level;

public class PropagationExample {
    private static final Logger logger = LoggerUtility.getLogger(); //get logger from LoggerUtility

    public void methodA() {
        methodB(); // Exception propagates from methodB to methodA
    }

    public void methodB() {
        methodC(); // Exception propagates from methodC to methodB
    }

    public void methodC() {
        throw new ArithmeticException("Error in methodC"); // Exception is thrown here
    }

    public static void main(String[] args) {
        PropagationExample example = new PropagationExample();
        try {
            example.methodA(); // Exception propagates to main
        } catch (ArithmeticException e) {
            logger.log(Level.SEVERE, "Exception caught in main: " + e.getMessage(), e); //log the exception
            System.out.println("Caught exception: " + e.getMessage()); //print message to console
        }
    }
}

// methodC -> methodB -> methodA -> main();
