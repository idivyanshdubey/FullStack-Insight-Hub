package com.iostream;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.*;

public class FileWriterExample {
    private static final Logger logger = Logger.getLogger(FileWriterExample.class.getName());

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

            logger.addHandler(fileHandler);
            logger.addHandler(consoleHandler);
            logger.setLevel(Level.INFO);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Logging setup failed", e);
        }
    }

    public static void main(String[] args) {
        String filePath = "C:\\Users\\10827307\\eclipse-workspace\\Viva\\src\\com\\iostream\\example.txt";
        logger.info("Attempting to write to file: " + filePath);

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("Hello, World!");
            writer.write("\nThis is an example of FileWriter.");
            logger.info("Data written successfully to file.");
            System.out.println("Data written successfully.");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error writing to file: " + e.getMessage(), e);
            System.err.println("Error: " + e.getMessage());
        }
    }
}
