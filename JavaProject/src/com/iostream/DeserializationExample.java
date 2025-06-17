package com.iostream;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.logging.*;

public class DeserializationExample {
    private static final Logger logger = Logger.getLogger(DeserializationExample.class.getName());

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
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Logging setup failed", e);
        }
    }

    public static void main(String[] args) {
        String filePath = "C:\\Users\\10827307\\eclipse-workspace\\Viva\\src\\com\\iostream\\exm.txt";

        try (FileInputStream fis = new FileInputStream(filePath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            logger.info("Attempting to deserialize object from: " + filePath);

            Student student = (Student) ois.readObject();
            logger.info("Deserialization successful. Name: " + student.name + ", Age: " + student.age);

            System.out.println("Name: " + student.name + ", Age: " + student.age);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Deserialization failed: " + e.getMessage(), e);
            System.err.println("Error: " + e.getMessage());
        }
    }
}
