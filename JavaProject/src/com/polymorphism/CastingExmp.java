package com.polymorphism;

import com.loggerutilities.LoggerUtility;
import java.util.logging.Logger;

class Animal {
    private static final Logger logger = LoggerUtility.getLogger();

    void sound() {
        logger.info("Animal makes a sound");
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    private static final Logger logger = LoggerUtility.getLogger();

    void bark() {
        logger.info("Dog barks");
        System.out.println("Dog barks");
    }
}

public class CastingExmp {
    private static final Logger logger = LoggerUtility.getLogger();

    public static void main(String[] args) {
        logger.info("Main method started");

        // Upcasting: Dog object is referred to by Animal reference
        Animal animal = new Dog();
        logger.info("Upcasting: Animal reference to Dog object");

        // Downcasting: Animal reference is cast back to Dog
        Dog dog = (Dog) animal;
        logger.info("Downcasting: Animal reference cast to Dog");

        // Calling Dog-specific method
        dog.bark();

        logger.info("Program completed");
    }
}
