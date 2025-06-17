package com.trycatchfinally; //package

import com.loggerutilities.LoggerUtility; //import logger utility
import java.util.ArrayList;
import java.util.Scanner; //import Scanner for user input
import java.util.logging.Logger;
import java.util.logging.Level;

public class IndexOutOfBoundsExample {
    private static final Logger logger = LoggerUtility.getLogger(); //get logger from LoggerUtility

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //create Scanner object for input

        // Create an ArrayList with 3 elements
        ArrayList<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");

        System.out.println("List size: " + list.size());

        try {
            System.out.print("Enter index to access (0 to " + (list.size() - 1) + "): "); //prompt user
            int index = scanner.nextInt(); //read index from user

            System.out.println("Element at index " + index + ": " + list.get(index)); //may throw IndexOutOfBoundsException

        } catch (IndexOutOfBoundsException e) { //catch invalid index access
            logger.log(Level.SEVERE, "IndexOutOfBoundsException caught: " + e.getMessage(), e); //log exception
            System.out.println("Caught an IndexOutOfBoundsException: " + e.getMessage()); //print message to console
        } catch (Exception e) { //catch any other unexpected exceptions
            logger.log(Level.SEVERE, "Unexpected error: " + e.getMessage(), e); //log unexpected error
            System.out.println("An unexpected error occurred: " + e.getMessage()); //print message to console
        } finally {
            scanner.close(); //close scanner
        }
    }
}
