package com.memorymanagement;

import java.io.IOException;
import java.util.logging.*;

class Example {
    private static final Logger logger = Logger.getLogger(Example.class.getName());

    static {
        try {
            LogManager.getLogManager().reset();

            // File Handler
            FileHandler fileHandler = new FileHandler("core_java_programs.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.INFO);

            // Console Handler
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new SimpleFormatter());
            consoleHandler.setLevel(Level.INFO);

            // Add handlers to logger
            logger.addHandler(fileHandler);
            logger.addHandler(consoleHandler);

            logger.setLevel(Level.INFO);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
    }

    // Instance block
    {
        logger.info("Instance block executed");
    }

    // Constructor
    Example() {
        logger.info("Constructor executed");
    }
}

public class InstanceBlock {
    public static void main(String[] args) {
        Example obj1 = new Example(); // Instance block runs before the constructor
        Example obj2 = new Example(); // Instance block runs again for the new object
    }
}
