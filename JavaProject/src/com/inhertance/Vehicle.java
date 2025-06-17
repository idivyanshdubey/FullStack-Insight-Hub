package com.inhertance;

import java.util.logging.Logger;

public class Vehicle {
    protected int speed = 50;
    protected static final Logger logger = Logger.getLogger(Vehicle.class.getName());

    public Vehicle() {
        logger.info("Vehicle constructor called. Speed set to " + speed);
    }
}
