package com.trycatchfinally; //package

import com.loggerutilities.LoggerUtility; //import logger utility
import java.util.logging.Logger;
import java.util.logging.Level;

class Exmp {
    public static void main(String[] args) {
        Exmp ex = new Exmp();
        Exmp1 obj = ex.new Exmp1(); //create instance of inner class

        try {
            obj.methodA(); //call method that may throw exception
        } catch (Exception e) {
            Logger logger = LoggerUtility.getLogger(); //get logger
            logger.log(Level.SEVERE, "Exception caught in main: " + e.getMessage(), e); //log exception
            System.out.println("Exception handled in main: " + e.getMessage()); //print message to console
        }
    }

    class Exmp1 {
        public void methodA() throws Exception {
            try {
                // Code that might throw an exception
                int result = 10 / 0; // ArithmeticException
            } catch (ArithmeticException e) {
                System.out.println("Caught in methodA, re-throwing..."); //inform user
                throw e; // Re-throwing the exception
            }
        }
    }
}
