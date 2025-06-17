package com.loggerutilities;

import java.io.IOException;
import java.util.logging.*;

public class LoggerUtility {
    private static final Logger logger = Logger.getLogger(LoggerUtility.class.getName());
    private static final String LOG_FILE_NAME = "core_java_programs.log";

    static {
        try {
            // Reset existing log configuration
            LogManager.getLogManager().reset();

            // Create a file handler with append mode
            FileHandler fileHandler = new FileHandler(LOG_FILE_NAME, true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.ALL);

            // Attach handler to logger
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);

            // Optional: Also log to console
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.SEVERE); // Only show severe logs in console
            logger.addHandler(consoleHandler);

        } catch (IOException e) {
            System.err.println("Logger setup failed: " + e.getMessage());
        }
    }

    public static Logger getLogger() {
        return logger;
    }
}
