package com.inhertance;

import java.util.logging.*;

class Parent {
    private static final Logger logger = Logger.getLogger(Parent.class.getName());

    Parent(String message) {
        System.out.println("Parent constructor called: " + message);
        logger.info("Parent constructor called with message: " + message);
    }
}

class Child extends Parent {
    private static final Logger logger = Logger.getLogger(Child.class.getName());

    Child() {
        super("Hello from Parent!"); // Calls the parameterized Parent constructor
        System.out.println("Child constructor called");
        logger.info("Child constructor called");
    }
}

public class WithArguMentExmp {
    private static final Logger logger = Logger.getLogger(WithArguMentExmp.class.getName());

    static {
        setupLogger(); // Ensure logger is configured before any logging
    }

    public static void main(String[] args) {
        logger.info("Main method started.");
        Child child = new Child();
        logger.info("Child object created.");
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
