package com.toString; //package

import com.loggerutilities.LoggerUtility; //import logger utility
import java.util.logging.Logger;
import java.util.logging.Level;

class Person11 implements Cloneable { // Implement Cloneable interface
    String name;
    int age;

    // Constructor
    Person11(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); // Call the clone() method from Object class
    }
}

public class CloneExample {
    private static final Logger logger = LoggerUtility.getLogger(); //get logger from LoggerUtility

    public static void main(String[] args) {
        try {
            // Create an original object
            Person11 original = new Person11("Alice", 25);

            // Create a clone of the original object
            Person11 cloned = (Person11) original.clone();

            // Display original and cloned object details
            System.out.println("Original: " + original.name + ", " + original.age);
            System.out.println("Cloned: " + cloned.name + ", " + cloned.age);

        } catch (CloneNotSupportedException e) {
            logger.log(Level.SEVERE, "CloneNotSupportedException caught: " + e.getMessage(), e); //log exception
            System.out.println("Cloning failed: " + e.getMessage()); //print message to console
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unexpected error: " + e.getMessage(), e); //log unexpected error
            System.out.println("An unexpected error occurred: " + e.getMessage()); //print message to console
        }
    }
}
