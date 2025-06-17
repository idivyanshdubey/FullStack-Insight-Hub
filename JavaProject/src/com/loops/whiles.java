package com.loops;

import java.util.logging.*;

public class whiles {
    private static final Logger logger = Logger.getLogger(whiles.class.getName());

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
        int i = 1, sum = 0;
        while (i <= 5) {
            sum += i;
            logger.info("i = " + i + ", Current Sum = " + sum);
            i++;
        }
        logger.info("Final Sum: " + sum);
        System.out.println("Sum: " + sum);
    }
}
