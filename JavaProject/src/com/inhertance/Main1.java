package com.inhertance;

import java.util.logging.*;

public class Main1 {
    private static final Logger logger = Logger.getLogger(Main1.class.getName());

    public static void main(String[] args) {
        setupLogger();

        logger.info("Main1 started.");
        Car2 myCar = new Car2();
        myCar.start(); // From parent class
        myCar.honk();  // From child class
        logger.info("Main1 completed.");
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
