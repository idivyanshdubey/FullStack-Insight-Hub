package com.inhertance;

import java.util.logging.*;

class Example {
    private static final Logger logger = Logger.getLogger(Example.class.getName());

    // Instance block
    {
        System.out.println("Instance block executed");
        logger.info("Instance block executed");
    }

    // Constructor
    Example() {
        System.out.println("Constructor executed");
        logger.info("Constructor executed");
    }
}

public class InstanceBlock {
    private static final Logger logger = Logger.getLogger(InstanceBlock.class.getName());

    static {
        setupLogger(); // Ensures logger is configured before any class is used
    }

    public static void main(String[] args) {
        logger.info("Main method started.");
        Example obj1 = new Example(); // Instance block runs before the constructor
        Example obj2 = new Example(); // Instance block runs again for the new object
        logger.info("Two Example objects created.");
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
