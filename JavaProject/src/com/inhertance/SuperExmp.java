package com.inhertance;

import java.util.logging.Logger;

class Parent11 {
    private static final Logger logger = Logger.getLogger(Parent11.class.getName());

    public Parent11() {
        System.out.println("Parent constructor executed.");
        logger.info("Parent11 constructor executed.");
    }
}
