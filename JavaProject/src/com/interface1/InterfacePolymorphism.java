package com.interface1;

import java.util.logging.*;

interface Shape {
    void draw();
}

class Circle implements Shape {
    private static final Logger logger = Logger.getLogger(Circle.class.getName());

    @Override
    public void draw() {
        logger.info("Drawing a Circle");
        System.out.println("Drawing a Circle");
    }
}

class Rectangle implements Shape {
    private static final Logger logger = Logger.getLogger(Rectangle.class.getName());

    @Override
    public void draw() {
        logger.info("Drawing a Rectangle");
        System.out.println("Drawing a Rectangle");
    }
}

public class InterfacePolymorphism {
    private static final Logger logger = Logger.getLogger(InterfacePolymorphism.class.getName());

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

        Shape shape1 = new Circle();
        Shape shape2 = new Rectangle();

        shape1.draw(); // Output: Drawing a Circle
        shape2.draw(); // Output: Drawing a Rectangle

        logger.info("Program ended.");
    }
}
