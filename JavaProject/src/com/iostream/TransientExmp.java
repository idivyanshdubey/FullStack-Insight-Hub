package com.iostream;

import java.io.*;
import java.util.logging.*;

class Person implements Serializable {
    String name;
    transient String password; // Marked as transient

    Person(String name, String password) {
        this.name = name;
        this.password = password;
    }
}

public class TransientExmp {
    private static final Logger logger = Logger.getLogger(TransientExmp.class.getName());

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
        try {
            Person person = new Person("Alice", "secret123");
            logger.info("Created Person object: Name = " + person.name + ", Password = " + person.password);

            // Serialize the object
            FileOutputStream fileOut = new FileOutputStream("person.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(person);
            out.close();
            fileOut.close();
            logger.info("Object serialized to person.ser");

            // Deserialize the object
            FileInputStream fileIn = new FileInputStream("person.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Person deserializedPerson = (Person) in.readObject();
            in.close();
            fileIn.close();
            logger.info("Object deserialized from person.ser");

            // Display the deserialized object
            System.out.println("Name: " + deserializedPerson.name);
            System.out.println("Password: " + deserializedPerson.password); // Will be null
            logger.info("Deserialized Person: Name = " + deserializedPerson.name + ", Password = " + deserializedPerson.password);

        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Error during serialization/deserialization", e);
            e.printStackTrace();
        }
    }
}
