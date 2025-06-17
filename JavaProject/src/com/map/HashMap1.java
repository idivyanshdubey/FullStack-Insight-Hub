package com.map;

import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.*;

public class HashMap1 {
    private static final Logger logger = Logger.getLogger(HashMap1.class.getName());

    static {
        try {
            LogManager.getLogManager().reset();

            // File Handler
            FileHandler fileHandler = new FileHandler("core_java_programs.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.INFO);

            // Console Handler
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new SimpleFormatter());
            consoleHandler.setLevel(Level.INFO);

            logger.addHandler(fileHandler);
            logger.addHandler(consoleHandler);
            logger.setLevel(Level.INFO);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Logging setup failed", e);
        }
    }

    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        // Add key-value pairs
        map.put(1, "Alice");
        map.put(2, "Bob");
        map.put(3, "Charlie");

        logger.info("Initial map: " + map);

        // User input for key to retrieve
        System.out.print("Enter key to retrieve value: ");
        int key = scanner.nextInt();
        String value = map.get(key);
        if (value != null) {
            logger.info("Value for key " + key + ": " + value);
        } else {
            logger.warning("Key " + key + " not found in map.");
        }

        // Check if a key or value exists
        System.out.print("Enter key to check existence: ");
        int checkKey = scanner.nextInt();
        logger.info("Contains key " + checkKey + "? " + map.containsKey(checkKey));

        scanner.nextLine(); // consume newline
        System.out.print("Enter value to check existence: ");
        String checkValue = scanner.nextLine();
        logger.info("Contains value '" + checkValue + "'? " + map.containsValue(checkValue));

        // Remove a key-value pair
        System.out.print("Enter key to remove: ");
        int removeKey = scanner.nextInt();
        map.remove(removeKey);
        logger.info("Updated Map after removal: " + map);

        scanner.close();
    }
}
