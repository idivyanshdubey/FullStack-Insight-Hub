package com.arraylist;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.logging.*;

public class RemoveDuplicates {
    private static final Logger logger = Logger.getLogger(RemoveDuplicates.class.getName());

    static {
        setupLogger();
    }

    public static void main(String[] args) {
        logger.info("Program started.");
        Scanner scanner = new Scanner(System.in);
        ArrayList<Character> list = new ArrayList<>();

        System.out.print("Enter the number of characters: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.println("Enter " + n + " characters (one per line):");
        for (int i = 0; i < n; i++) {
            String input = scanner.nextLine();
            if (!input.isEmpty()) {
                char ch = input.charAt(0);
                list.add(ch);
                logger.info("Added character: " + ch);
            }
        }

        System.out.println("Original list with duplicates: " + list);
        logger.info("Original list: " + list);

        TreeSet<Character> set = new TreeSet<>(list);
        System.out.println("List after removing duplicates: " + set);
        logger.info("List after removing duplicates: " + set);

        scanner.close();
    }

    private static void setupLogger() {
        try {
            LogManager.getLogManager().reset();
            FileHandler fh = new FileHandler("core_java_programs.log", true);
            fh.setFormatter(new SimpleFormatter());
            Logger rootLogger = Logger.getLogger("");
            rootLogger.addHandler(fh);
            rootLogger.setLevel(Level.INFO);
        } catch (Exception e) {
            System.err.println("Logger setup failed: " + e.getMessage());
        }
    }
}
