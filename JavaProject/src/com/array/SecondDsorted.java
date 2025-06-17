package com.array;

import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.*;

public class SecondDsorted {
    private static final Logger logger = Logger.getLogger(SecondDsorted.class.getName());

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

        logger.info("Original matrix: " + Arrays.deepToString(matrix));

        for (int i = 0; i < matrix.length; i++) {
            Arrays.sort(matrix[i]);
            logger.info("Sorted row " + i + ": " + Arrays.toString(matrix[i]));
        }

        System.out.println("Sorted matrix (row-wise):");
        System.out.println(Arrays.deepToString(matrix));
        logger.info("Final sorted matrix: " + Arrays.deepToString(matrix));

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
