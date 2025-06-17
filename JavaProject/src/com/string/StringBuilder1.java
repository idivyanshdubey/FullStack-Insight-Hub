package com.string;

import com.loggerutilities.LoggerUtility;
import java.util.Scanner;
import java.util.logging.Logger;

public class StringBuilder1 {
    private static final Logger logger = LoggerUtility.getLogger();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a string for StringBuilder:");
        String input1 = scanner.nextLine();
        StringBuilder sb = new StringBuilder(input1);
        sb.append(" World");
        logger.info("StringBuilder append: " + sb);
        System.out.println(sb);

        System.out.println("Enter a string to reverse:");
        String str = scanner.nextLine();
        StringBuilder str1 = new StringBuilder(str);
        str1 = str1.reverse();
        String str2 = new String(str1);
        logger.info("Reversed string: " + str2);
        System.out.println(str2);

        System.out.println("Enter a string for StringBuffer (min 4 characters):");
        String input3 = scanner.nextLine();
        StringBuffer sb11 = new StringBuffer(input3);
        if (sb11.length() >= 4) {
            String sb12 = sb11.substring(2, 4);
            logger.info("Substring (2,4): " + sb12);
            System.out.println(sb12);
        } else {
            logger.warning("Input too short for substring (2,4)");
            System.out.println("Input too short for substring (2,4)");
        }

        System.out.println("Enter a string to test immutability:");
        String strr = scanner.nextLine();
        strr.concat(" World");
        logger.info("Immutable string result: " + strr);
        System.out.println(strr);

        System.out.println("Enter two strings to compare:");
        String s1 = scanner.nextLine();
        String s2 = scanner.nextLine();
        String s3 = new String();
        logger.info("s1 == s2: " + (s1 == s2));
        logger.info("s1 == s3: " + (s1 == s3));
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);

        scanner.close();
    }
}
