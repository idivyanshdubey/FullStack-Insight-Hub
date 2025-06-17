package com.memorymanagement;

import com.loggerutilities.LoggerUtility;
import java.util.logging.Logger;

public class GarbageCollectionExample {
    private static final Logger logger = LoggerUtility.getLogger();

    public static void main(String[] args) {
        logger.info("GarbageCollectionExample started");

        GarbageCollectionExample obj = new GarbageCollectionExample();
        logger.info("Object created: " + obj);

        obj = null; // Make the object eligible for garbage collection
        logger.info("Object reference set to null");

        System.gc(); // Request the garbage collector
        logger.info("System.gc() called");

        logger.info("GarbageCollectionExample completed");
    }

    @Override
    protected void finalize() {
        logger.info("finalize() method called - object is being garbage collected");
        System.out.println("Garbage collector called!");
    }
}
