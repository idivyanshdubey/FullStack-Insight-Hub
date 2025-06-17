package com.inner;

import java.util.Scanner;
import java.util.logging.*;

class OuterClass {
    private String message;

    private static final Logger logger = Logger.getLogger(OuterClass.class.getName());

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
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Logger setup failed", e);
        }
    }

    public OuterClass(String message) {
        this.message = message;
        logger.info("OuterClass initialized with message: " + message);
    }

    class InnerClass { // Member Inner Class
        public void display() {
            logger.info("InnerClass display() called. Message: " + message);
            System.out.println(message); // Access outer class field
        }
    }
}

public class MemberInnerClassExample { // can access all the members of static and non-static members of the outer class
    private static final Logger logger = Logger.getLogger(MemberInnerClassExample.class.getName());

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
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Logger setup failed", e);
        }
    }

    public static void main(String[] args) {
        logger.info("Program started.");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a message for the Inner Class to display: ");
        String userMessage = scanner.nextLine();

        OuterClass outer = new OuterClass(userMessage); // instance can be created after the instance of outer class is created
        OuterClass.InnerClass inner = outer.new InnerClass();
        inner.display();
        inner.display();

        logger.info("Program ended.");
        scanner.close();
    }
}
