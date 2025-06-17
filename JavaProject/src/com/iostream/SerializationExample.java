package com.iostream;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.*;

class Student implements Serializable {
    // private static final long serialVersionUID = 1L; // Best practice
    String name;
    int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public class SerializationExample {
    private static final Logger logger = Logger.getLogger(SerializationExample.class.getName());

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
        Student student = new Student("Alice", 20);
        logger.info("Created Student object: Name = " + student.name + ", Age = " + student.age);

        String filePath = "C:\\Users\\10827307\\eclipse-workspace\\Viva\\src\\com\\iostream\\exm.txt";

        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(student);
            logger.info("Object serialized successfully to: " + filePath);
            System.out.println("Object serialized successfully.");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Serialization failed: " + e.getMessage(), e);
            System.err.println("Error: " + e.getMessage());
        }
    }
}
