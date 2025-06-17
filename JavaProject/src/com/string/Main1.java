package com.string;

import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.*;

public class Main1 {
    private static final Logger logger = Logger.getLogger(Main1.class.getName());

    static {
        try {
            // Set up a FileHandler to write logs to a file
            FileHandler fileHandler = new FileHandler("core_java_programs.log", true); // true = append mode
            fileHandler.setFormatter(new SimpleFormatter()); // Use simple text format
            logger.addHandler(fileHandler);

            // Optional: Set the logging level
            logger.setLevel(Level.INFO);
            fileHandler.setLevel(Level.INFO);

            // Disable default console handler
            Logger rootLogger = Logger.getLogger("");
            Handler[] handlers = rootLogger.getHandlers();
            for (Handler handler : handlers) {
                if (handler instanceof ConsoleHandler) {
                    rootLogger.removeHandler(handler);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Print to console if logger setup fails
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a string to tokenize:");
        String input = scanner.nextLine();

        System.out.println("Enter delimiters (e.g., ', '):");
        String delimiters = scanner.nextLine();

        StringTokenizer st = new StringTokenizer(input, delimiters);

        logger.info("Tokens:");
        while (st.hasMoreTokens()) {
            logger.info(st.nextToken());
        }

        scanner.close();
    }
}
