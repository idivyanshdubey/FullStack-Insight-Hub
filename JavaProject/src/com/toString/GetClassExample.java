package com.toString; //package

import com.loggerutilities.LoggerUtility; //import logger utility
import java.util.logging.Logger;

public class GetClassExample {
    private static final Logger logger = LoggerUtility.getLogger(); //get logger from LoggerUtility

    public static void main(String[] args) {
        String str = "Hello"; //create a string
        Class<?> clazz = str.getClass(); //get class information

        logger.info("Class of str: " + clazz.getName()); //log class name
        System.out.println(clazz); //print class information
    }
}
