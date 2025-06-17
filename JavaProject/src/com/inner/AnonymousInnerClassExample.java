package com.inner;

import java.util.logging.*;

interface Greeting { // making interface for anonymous inner class
    void sayHello();
}

public class AnonymousInnerClassExample {
    private static final Logger logger = Logger.getLogger(AnonymousInnerClassExample.class.getName());

    static {
        try {
            LogManager.getLogManager().reset();

            FileHandler fileHandler = new FileHandler("core_java_programs.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.INFO);

            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new SimpleFormatter());
            consoleHandler.setLevel(Level.INFO);

            logger.addHandler(fileHandler);
            logger.addHandler(consoleHandler);
            logger.setLevel(Level.INFO);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Logger setup failed", e);
        }
    }

    public static void main(String[] args) {
        logger.info("Creating anonymous inner class instance.");

        Greeting greeting = new Greeting() { // Anonymous Inner Class instance
            @Override
            public void sayHello() { // method
                logger.info("sayHello() method called.");
                System.out.println("Hello from Anonymous Inner Class");
            }
        }; // first we create the instance then we override the interface method then we close the instance with semi colon then we call the method 

        greeting.sayHello(); // calling
        logger.info("Program execution completed.");
    }
}
