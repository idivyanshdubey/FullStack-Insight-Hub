package com.inhertance;

import java.util.logging.Logger;

class Car2 extends Vehicle1 {
    private static final Logger logger = Logger.getLogger(Car2.class.getName());

    public Car2() {
        super();
        System.out.println("child cons called");
        logger.info("Car2 constructor called.");
    }

    void honk() {
        System.out.println("Car honks");
        logger.info("Car2 honk method called.");
    }
}
