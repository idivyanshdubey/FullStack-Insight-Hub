package com.array;

import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.*;

public class Binary1D {
    private static final Logger logger = Logger.getLogger(Binary1D.class.getName());

    static {
        setupLogger();
    }

    public static void main(String[] args) {
        logger.info("Program started.");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();
        int[] array = new int[n];

        System.out.println("Enter " + n + " integers:");
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        logger.info("User entered array: " + Arrays.toString(array));

        Arrays.sort(array);
        logger.info("Sorted array: " + Arrays.toString(array));

        System.out.print("Enter the number to search: ");
        int key = scanner.nextInt();
        logger.info("User wants to search for: " + key);

        int index = Arrays.binarySearch(array, key);
        if (index >= 0) {
            System.out.println("Found at index: " + index);
            logger.info("Element found at index: " + index);
        } else {
            System.out.println("Element not found.");
            logger.info("Element not found.");
        }

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
