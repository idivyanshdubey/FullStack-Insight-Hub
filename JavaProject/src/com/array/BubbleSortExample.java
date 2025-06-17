package com.array;

import java.util.Scanner;
import java.util.logging.*;

public class BubbleSortExample {
    private static final Logger logger = Logger.getLogger(BubbleSortExample.class.getName());

    static {
        setupLogger();
    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                logger.info("Comparing " + arr[j] + " and " + arr[j + 1]);
                if (arr[j] > arr[j + 1]) {
                    // Swap elements
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                    logger.info("Swapped " + arr[j] + " and " + arr[j + 1]);
                }
            }
            if (!swapped) {
                logger.info("No swaps in this pass, array is sorted.");
                break;
            }
        }
    }

    public static void main(String[] args) {
        logger.info("Program started.");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();
        int[] arr = new int[n];

        System.out.println("Enter " + n + " integers:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        logger.info("User entered array: " + java.util.Arrays.toString(arr));
        bubbleSort(arr);
        logger.info("Sorted array: " + java.util.Arrays.toString(arr));

        System.out.println("Sorted array:");
        for (int num : arr) {
            System.out.print(num + " ");
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
