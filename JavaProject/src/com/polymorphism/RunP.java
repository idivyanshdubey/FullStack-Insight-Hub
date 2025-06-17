package com.polymorphism;

import com.loggerutilities.LoggerUtility;
import java.util.logging.Logger;

class RunP extends RunPoly {
    private static final Logger logger = LoggerUtility.getLogger();

    @Override
    void sound() {
        logger.info("RunP: Car honks.");
        System.out.println("Car honks.");
    }
}
