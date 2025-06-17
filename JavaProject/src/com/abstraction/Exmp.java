package com.abstraction;

import java.util.Scanner;
import java.util.logging.*;

// Interface
interface Animal {
    void eat();
    void sleep();
}

// Abstract Class
abstract class Mammal implements Animal {
    abstract void walk();

    void breathe() {
        System.out.println("Mammals breathe air.");
    }
}

// Concrete Class
class Dog extends Mammal {
    private static final Logger logger = Logger.getLogger(Dog.class.getName());

    @Override
    public void eat() {
        System.out.println("Dog eats meat.");
        logger.info("Dog is eating.");
    }

    @Override
    public void sleep() {
        System.out.println("Dog sleeps peacefully.");
        logger.info("Dog is sleeping.");
    }

    @Override
    void walk() {
        System.out.println("Dog walks on four legs.");
        logger.info("Dog is walking.");
    }

    @Override
    void breathe() {
        super.breathe();
        logger.info("Dog is breathing.");
    }
}

// Main Method to test
public class Exmp {
    private static final Logger logger = Logger.getLogger(Exmp.class.getName());

    static {
        setupLogger();
    }

    public static void main(String[] args) {
        logger.info("Program started.");
        Scanner scanner = new Scanner(System.in);
        Dog dog = new Dog();

        while (true) {
            System.out.println("\nChoose an action for the dog:");
            System.out.println("1. Eat");
            System.out.println("2. Sleep");
            System.out.println("3. Walk");
            System.out.println("4. Breathe");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();
            logger.info("User selected option: " + choice);

            switch (choice) {
                case "1":
                    dog.eat();
                    break;
                case "2":
                    dog.sleep();
                    break;
                case "3":
                    dog.walk();
                    break;
                case "4":
                    dog.breathe();
                    break;
                case "5":
                    logger.info("Program exited by user.");
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    logger.warning("Invalid user input: " + choice);
            }
        }
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
