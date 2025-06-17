package com.inhertance;

import java.util.logging.*;

class Animal {
    String type;
    private static final Logger logger = Logger.getLogger(Animal.class.getName());

    Animal(String type) {
        this.type = type;
        System.out.println("Animal constructor called with type: " + type);
        logger.info("Animal constructor called with type: " + type);
    }

    void sound() {
        System.out.println("Animal makes a sound");
        logger.info("Animal sound method called.");
    }
}

class Dog extends Animal {
    String breed;
    private static final Logger logger = Logger.getLogger(Dog.class.getName());

    Dog(String type, String breed) {
        super(type);
        this.breed = breed;
        System.out.println("Dog constructor called with breed: " + breed);
        logger.info("Dog constructor called with breed: " + breed);
    }

    void bark() {
        System.out.println("Dog barks");
        logger.info("Dog bark method called.");
    }
}

public class InheritanceWithLogger {
    private static final Logger logger = Logger.getLogger(InheritanceWithLogger.class.getName());

    public static void main(String[] args) {
        setupLogger();

        logger.info("Main method started.");
        Dog dog = new Dog("Mammal", "Labrador");
        dog.sound(); // From Animal
        dog.bark();  // From Dog
        logger.info("Main method completed.");
    }

    private static void setupLogger() {
        try {
            LogManager.getLogManager().reset();
            FileHandler fh = new FileHandler("core_java_programs.log", true);
            fh.setFormatter(new SimpleFormatter());
            Logger rootLogger = Logger.getLogger("");
            rootLogger.addHandler(fh);
            rootLogger.setLevel(Level.INFO);
        } catch (Exception e) {
            System.err.println("Logger setup failed: " + e.getMessage());
        }
    }
}
