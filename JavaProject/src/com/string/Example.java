package com.string;

import com.loggerutilities.LoggerUtility;
import java.util.Scanner;
import java.util.logging.Logger;

public class Example {
    private static final Logger logger = LoggerUtility.getLogger();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String str = scanner.nextLine();
        logger.info("User input: " + str);
        scanner.close();

        String vowels = String.join("", str.toLowerCase().split("[^aeiou]"));
        logger.info("Vowels extracted: " + vowels);
        System.out.println("Vowels in the string: " + vowels);
    }
}
