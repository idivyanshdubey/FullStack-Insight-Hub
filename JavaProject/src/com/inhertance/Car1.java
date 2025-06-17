package com.inhertance;

import java.util.logging.Logger;

class Car1 extends Vehicle {
    private int speed = 100;
    private static final Logger logger = Logger.getLogger(Car1.class.getName());

    public Car1() {
        logger.info("Car1 constructor called. Speed set to " + speed);
    }

    void display() {
        logger.info("Display method called.");
        System.out.println("Parent Speed: " + super.speed);
        System.out.println("Child Speed: " + speed);
        logger.info("Displayed Parent Speed: " + super.speed + ", Child Speed: " + speed);
    }
}
