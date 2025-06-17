package com.set;

import com.loggerutilities.LoggerUtility;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Logger;

public class Set1 {
    private static final Logger logger = LoggerUtility.getLogger();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashSet<String> set = new HashSet<>();

        System.out.println("Enter 5 fruits (duplicates will be ignored):");
        for (int i = 0; i < 5; i++) {
            String fruit = scanner.nextLine();
            boolean added = set.add(fruit);
            logger.info("Attempted to add: " + fruit + " | Added: " + added);
        }

        System.out.println("HashSet: " + set);
        logger.info("Initial HashSet: " + set);

        System.out.println("Check if 'Banana' is in the set:");
        boolean containsBanana = set.contains("Banana");
        logger.info("Contains 'Banana'? " + containsBanana);
        System.out.println("Contains 'Banana'? " + containsBanana);

        System.out.println("Removing 'Cherry' from the set...");
        boolean removed = set.remove("Cherry");
        logger.info("Removed 'Cherry'? " + removed);
        System.out.println("Updated HashSet: " + set);

        logger.info("Iterating over HashSet:");
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String item = it.next();
            logger.info("Iterator item: " + item);
            System.out.println("Printing with iterator: " + item);
            if (item.equals("Apple")) {
                logger.info("Found 'Apple' in iteration");
                System.out.println("Found 'Apple'");
            }
        }

        scanner.close();
    }
}
