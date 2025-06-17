package com.interface1;

import java.util.logging.*;

@FunctionalInterface
interface MathOperation {
    public abstract int operate(int a, int b); // by default public abstract
}

public class FunctionalInterface1 {
    private static final Logger logger = Logger.getLogger(FunctionalInterface1.class.getName());

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
            logger.log(Level.SEVERE, "Logger setup failed", e);
        }
    }

    public static void main(String[] args) {
        logger.info("Program started.");

        MathOperation addition = (a, b) -> a + b;
        int result = addition.operate(5, 10);

        logger.info("Performed addition: 5 + 10 = " + result);
        System.out.println(result); // Output: 15

        logger.info("Program ended.");
    }
}
