package com.polymorphism;

import com.loggerutilities.LoggerUtility;
import java.util.logging.Logger;

public class RunPoly {
    private static final Logger logger = LoggerUtility.getLogger();

    void sound() {
        logger.info("RunPoly: Vehicle sound.");
        System.out.println("Vehicle sound.");
    }
}
