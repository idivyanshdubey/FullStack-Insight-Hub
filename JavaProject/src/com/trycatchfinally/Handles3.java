package com.trycatchfinally; //package

import com.loggerutilities.LoggerUtility; //import logger utility
import java.util.Scanner; //import Scanner for user input
import java.util.logging.Logger;
import java.util.logging.Level;

public class Handles3 {

    private static final Logger logger = LoggerUtility.getLogger(); //get logger from LoggerUtility

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //create Scanner object for input

        try {
            System.out.print("Enter numerator: "); //prompt user
            int numerator = scanner.nextInt(); //read numerator

            System.out.print("Enter denominator: "); //prompt user
            int denominator = scanner.nextInt(); //read denominator

            int result = numerator / denominator; //may throw ArithmeticException
            System.out.println("Result: " + result); //print result

        } catch (ArithmeticException e) { //catch unchecked exception
            logger.log(Level.SEVERE, "ArithmeticException caught: Division by zero.", e); //log exception
            System.out.println("Exception caught: Division by zero."); //print message to console
        } catch (Exception e) { //catch any other unexpected exceptions
            logger.log(Level.SEVERE, "Unexpected error: " + e.getMessage(), e); //log unexpected error
            System.out.println("An unexpected error occurred: " + e.getMessage()); //print message to console
        } finally {
            scanner.close(); //close scanner
        }
    }
}

// unchecked exception not required to be handled
