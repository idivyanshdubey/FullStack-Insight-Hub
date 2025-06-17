package com.array;

import java.util.Scanner;
import java.util.logging.*;

public class Searching2D {
    private static final Logger logger = Logger.getLogger(Searching2D.class.getName());

    static {
        setupLogger();
    }

    public static void main(String[] args) {
        logger.info("Program started.");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Enter number of columns: ");
        int cols = scanner.nextInt();

        int[][] matrix = new int[rows][cols];

        System.out.println("Enter elements of the matrix:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        System.out.print("Enter the number to search: ");
        int key = scanner.nextInt();
        logger.info("User wants to search for: " + key);

        boolean found = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                logger.info("Checking value at [" + i + "][" + j + "]: " + matrix[i][j]);
                if (matrix[i][j] == key) {
                    found = true;
                    System.out.println("Found at row " + i + ", column " + j);
                    logger.info("Element found at row " + i + ", column " + j);
                    break;
                }
            }
        }

        if (!found) {
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
