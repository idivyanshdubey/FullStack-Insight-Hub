package com.trycatchfinally; //package

import com.loggerutilities.LoggerUtility; //import logger utility
import java.io.File;
import java.io.FileReader;
import java.util.Scanner; //import Scanner for user input
import java.util.logging.Logger;
import java.util.logging.Level;

public class Handles2 {

    private static final Logger logger = LoggerUtility.getLogger(); //get logger from LoggerUtility

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //create Scanner object for input

        try {
            System.out.print("Enter the file name to read: "); //prompt user
            String fileName = scanner.nextLine(); //read file name from user

            File file = new File(fileName); //create File object
            FileReader fr = new FileReader(file); // Throws FileNotFoundException if file doesn't exist

            System.out.println("File found and ready to read."); //success message

        } catch (Exception e) { //catch checked exception
            logger.log(Level.SEVERE, "Exception caught while reading file: " + e.getMessage(), e); //log exception
            System.out.println("File not found!"); //print message to console
        } finally {
            scanner.close(); //close scanner
        }
    }
}

//checked exception mandatory to be handled
