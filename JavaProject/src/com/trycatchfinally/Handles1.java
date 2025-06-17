package com.trycatchfinally; //package

import com.loggerutilities.LoggerUtility; //import logger utility
import java.util.Scanner; //import Scanner for user input
import java.util.logging.Logger;
import java.util.logging.Level;

public class Handles1 {

    private static final Logger logger = LoggerUtility.getLogger(); //get logger from LoggerUtility

    // Method with throws declaration
    public static void checkNumber(int num) throws Exception {
        if (num < 0) {
            throw new Exception("Number must be positive."); //throw exception if number is negative
        } else {
            System.out.println("Valid number entered: " + num); //print valid number
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //create Scanner object for input

        try {
            System.out.print("Enter a number: "); //prompt user
            int number = scanner.nextInt(); //read user input
            checkNumber(number); //call method that may throw exception
        } catch (Exception e) { //catch any exception
            logger.log(Level.SEVERE, "Exception caught: " + e.getMessage(), e); //log the exception
            System.out.println(e.getMessage()); //print message to console
        } finally {
            scanner.close(); //close scanner
        }
    }
}
