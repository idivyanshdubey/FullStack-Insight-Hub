package com.inner;

import java.util.Scanner;
import java.util.logging.*;

class OuterClass12 { // final will work
    private static final Logger logger = Logger.getLogger(OuterClass12.class.getName());

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

    public void methodWithInnerClass(String message) {
        logger.info("Entered methodWithInnerClass with message: " + message);

        class LocalInnerClass { // Local Inner Class a class defined inside a method or a block
            public void display() {
                logger.info("Displaying message from LocalInnerClass.");
                System.out.println(message);
            }
        }

        LocalInnerClass inner = new LocalInnerClass(); // the scope of inner class is limited to the method and can access fields like final and effectively final
        inner.display();
    }
}

public class LocalInnerClassExample {
    private static final Logger logger = Logger.getLogger(LocalInnerClassExample.class.getName());

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

        System.out.print("Enter a message for the Local Inner Class: ");
        String userMessage = scanner.nextLine();

        OuterClass12 outer = new OuterClass12();
        outer.methodWithInnerClass(userMessage);

        logger.info("Program ended.");
        scanner.close();
    }
}
