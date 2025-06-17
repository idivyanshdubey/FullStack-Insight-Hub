package com.inner;

import java.io.IOException;
import java.util.logging.*;

class Example {
    static int count;

    private static final Logger logger = Logger.getLogger(Example.class.getName());

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

            count = 10;
            logger.info("Static block executed. Count initialized to " + count);
            System.out.println("Static block executed");

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Logger setup failed", e);
        }
    }

    public static void main(String[] args) {
        logger.info("Main method started.");
        System.out.println("Count: " + count);
        logger.info("Count value printed: " + count);
    }
}
