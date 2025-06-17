package com.constructor;

import java.util.Scanner;
import java.util.logging.*;

class Person {
    private static final Logger logger = Logger.getLogger(Person.class.getName());

    String name;
    int age;

    // Constructor
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        logger.info("Parameterized constructor called with name: " + name + ", age: " + age);
    }

    // Copy Constructor
    public Person(Person original) {
        this.name = original.name;
        this.age = original.age;
        logger.info("Copy constructor called. Copied name: " + name + ", age: " + age);
    }

    void display() {
        System.out.println("Name: " + name + ", Age: " + age);
        logger.info("display() called. Name: " + name + ", Age: " + age);
    }
}

public class CopyConstructorExmp {
    private static final Logger logger = Logger.getLogger(CopyConstructorExmp.class.getName());

    static {
        setupLogger();
    }

    public static void main(String[] args) {
        logger.info("Program started.");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter age: ");
        int age = scanner.nextInt();

        Person person1 = new Person(name, age);
        Person person2 = new Person(person1); // Creating a copy

        person2.display();

        scanner.close();
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
