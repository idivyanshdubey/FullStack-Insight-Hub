package com.iostream;

import java.io.FileReader;
import java.io.IOException;
import java.util.logging.*;

public class CharacterStreamExample {
    private static final Logger logger = Logger.getLogger(CharacterStreamExample.class.getName());

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

        try (FileReader fr = new FileReader(filePath)) {
            logger.info("Reading from file: " + filePath);

            int charData;
            while ((charData = fr.read()) != -1) {
                System.out.print((char) charData);
                logger.fine("Read character: " + (char) charData); // Use FINE for detailed logs
            }

            logger.info("File reading completed successfully.");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading file: " + e.getMessage(), e);
            System.err.println("Error: " + e.getMessage());
        }
    }
}
