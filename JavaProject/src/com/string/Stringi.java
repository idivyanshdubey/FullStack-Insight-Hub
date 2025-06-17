package com.string;

import com.loggerutilities.LoggerUtility;
import java.util.Scanner;
import java.util.logging.Logger;

public class Stringi {
    private static final Logger logger = LoggerUtility.getLogger();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a word of your choice:");
        String str = scanner.nextLine();
        logger.info("User input: " + str);

        // Convert to uppercase
        String upper = str.toUpperCase();
        logger.info("Uppercase: " + upper);
        System.out.println("Uppercase: " + upper);

        // Count vowels
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char a = Character.toLowerCase(str.charAt(i));
            if (a == 'a' || a == 'e' || a == 'i' || a == 'o' || a == 'u') {
                count++;
            }
        }
        logger.info("Vowel count: " + count);
        System.out.println("Number of vowels: " + count);

        // Reverse the string
        String str1 = "";
        for (int i = 0; i < str.length(); i++) {
            char s = str.charAt(i);
            str1 = s + str1;
        }
        logger.info("Reversed string: " + str1);
        System.out.println("Reversed: " + str1);

        scanner.close();
    }
}
