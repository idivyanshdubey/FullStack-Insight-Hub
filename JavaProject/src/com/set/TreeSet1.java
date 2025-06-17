package com.set;

import com.loggerutilities.LoggerUtility;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.logging.Logger;

public class TreeSet1 {
    private static final Logger logger = LoggerUtility.getLogger();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TreeSet<String> treeSet = new TreeSet<>();

        System.out.println("Enter 5 fruits to add to the TreeSet (sorted, no duplicates):");
        for (int i = 0; i < 5; i++) {
            String fruit = scanner.nextLine();
            boolean added = treeSet.add(fruit);
            logger.info("Attempted to add: " + fruit + " | Added: " + added);
        }

        // Display the sorted TreeSet
        System.out.println("TreeSet (sorted): " + treeSet);
        logger.info("Final TreeSet: " + treeSet);

        // Display first and last elements
        if (!treeSet.isEmpty()) {
            String first = treeSet.first();
            String last = treeSet.last();
            logger.info("First element: " + first);
            logger.info("Last element: " + last);
            System.out.println("First element: " + first);
            System.out.println("Last element: " + last);
        } else {
            logger.warning("TreeSet is empty. No first or last element.");
            System.out.println("TreeSet is empty.");
        }

        scanner.close();
    }
}
