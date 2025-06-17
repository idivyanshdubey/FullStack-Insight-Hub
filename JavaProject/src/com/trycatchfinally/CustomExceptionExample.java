package com.trycatchfinally; //package

import com.loggerutilities.LoggerUtility; //import logger utility
import java.util.Scanner; //import Scanner for user input
import java.util.logging.Logger;
import java.util.logging.Level;

// Custom exception class
class CustomException extends Exception { //customException extend Exception
    CustomException(String message) { //constructor
        super(message); //call message
    }
}

public class CustomExceptionExample {
    private static final Logger logger = LoggerUtility.getLogger(); //get logger from LoggerUtility

    public static void main(String[] args) { //entry point
        Scanner scanner = new Scanner(System.in); //create Scanner object for input

        try {
            System.out.print("Enter your age: "); //prompt user
            int age = scanner.nextInt(); //read user input
            checkAge(age); // Throws CustomException if age < 18
        } catch (CustomException e) { //Catches the CustomException
            logger.log(Level.SEVERE, "CustomException caught: " + e.getMessage(), e); //log the exception
            System.out.println("Caught exception: " + e.getMessage()); //print message to console
        } catch (Exception e) { //Catches any other unexpected exceptions
            logger.log(Level.SEVERE, "Unexpected error: " + e.getMessage(), e); //log unexpected errors
            System.out.println("An unexpected error occurred: " + e.getMessage()); //print message to console
        } finally {
            scanner.close(); //close scanner
        }
    }

    public static void checkAge(int age) throws CustomException {
        if (age < 18) {
            throw new CustomException("Age must be 18 or above."); //throw custom exception
        } else {
            System.out.println("Access granted. You are eligible."); //success message
        }
    }
}

//throw when you want to  explicitly throw an exception control is passe
//throws when you want to declare an exception that method may throw an exception
//why for readability,seperation ,debugging
