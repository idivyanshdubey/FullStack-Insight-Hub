package com.inhertance;

import java.util.logging.Logger;

public class Vehicle1 {
    private static final Logger logger = Logger.getLogger(Vehicle1.class.getName());

    public Vehicle1() {
        System.out.println("parent cons call");
        logger.info("Vehicle1 constructor called.");
    }

    void start() {
        System.out.println("Vehicle starts");
        logger.info("Vehicle1 start method called.");
    }
}
