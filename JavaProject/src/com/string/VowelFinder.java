package com.string;

import com.loggerutilities.LoggerUtility;
import java.util.Scanner;
import java.util.logging.Logger;

public class VowelFinder {
    private static final Logger logger = LoggerUtility.getLogger();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a string to find vowels:");
        String str = scanner.nextLine();
        logger.info("User input: " + str);

        String str1 = str.toLowerCase();
        StringBuilder str2 = new StringBuilder();

        for (int i = 0; i < str1.length(); i++) {
            char a = str1.charAt(i);
            if (a == 'a' || a == 'e' || a == 'i' || a == 'o' || a == 'u') {
                str2.append(a);
            }
        }

        String vowelsFound = str2.toString();
        logger.info("Vowels found: " + vowelsFound);
        System.out.println("Vowels in the string: " + vowelsFound);

        scanner.close();
    }
}
