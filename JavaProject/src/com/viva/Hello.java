package com.viva;

import java.util.logging.Logger;

import com.loggerutilities.LoggerUtility;

public class Hello {
	public static void main(String[] args) {
		final Logger logger = LoggerUtility.getLogger();
		System.out.println("hello");

        logger.info("Hello program started.");
        logger.info("Printed 'hello' to console.");

		
	}

}
