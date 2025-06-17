package com.iostream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.*;

public class ByteStreamExample {
    private static final Logger logger = Logger.getLogger(ByteStreamExample.class.getName());

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
        String inputPath = "C:\\Users\\10827307\\eclipse-workspace\\Viva\\src\\com\\iostream\\girl.png";
        String outputPath = "C:\\Users\\10827307\\eclipse-workspace\\Viva\\src\\com\\iostream\\output.png";

        try (FileInputStream fis = new FileInputStream(inputPath);
             FileOutputStream fos = new FileOutputStream(outputPath)) {

            logger.info("Starting file copy from: " + inputPath + " to: " + outputPath);

            int byteData;
            while ((byteData = fis.read()) != -1) {
                fos.write(byteData);
            }

            logger.info("File copied successfully.");
            System.out.println("File copied successfully.");

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error during file copy: " + e.getMessage(), e);
            System.err.println("Error: " + e.getMessage());
        }
    }
}
