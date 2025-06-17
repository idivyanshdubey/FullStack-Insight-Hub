package com.polymorphism;

import com.loggerutilities.LoggerUtility;
import java.util.logging.Logger;

public class Main1 {
    private static final Logger logger = LoggerUtility.getLogger();

    public static void main(String[] args) {
        logger.info("Main1 started");
        RunP p = new RunP();
        p.sound();
        logger.info("Main1 completed");
    }
}
