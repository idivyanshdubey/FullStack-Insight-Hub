package com.inner;

import java.util.Scanner;
import java.util.logging.*;

class OuterClass1 {
    private static String message;

    static class StaticNestedClass {
        public void display() {
            Logger logger = Logger.getLogger(StaticNestedClass.class.getName());
            logger.info("Displaying message: " + message);
            System.out.println(message);
        }
    }

    public static void setMessage(String msg) {
        message = msg;
    }
}

public class StaticNestedClassExample {
    private static final Logger logger = Logger.getLogger(StaticNestedClassExample.class.getName());

    public static void main(String[] args) {
        setupLogger();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a message for the static nested class: ");
        String userInput = scanner.nextLine();
        OuterClass1.setMessage(userInput);
        logger.info("User input received: " + userInput);

        OuterClass1.StaticNestedClass nested = new OuterClass1.StaticNestedClass();
        nested.display();

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
            System.err.println("Failed to set up logger: " + e.getMessage());
        }
    }
}
