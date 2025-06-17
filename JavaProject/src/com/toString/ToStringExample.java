package com.toString;

import com.loggerutilities.LoggerUtility;
import java.util.Scanner;
import java.util.logging.Logger;

class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}

public class ToStringExample {
    private static final Logger logger = LoggerUtility.getLogger();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            logger.info("User entered name: " + name);

            System.out.print("Enter age: ");
            int age = scanner.nextInt();
            logger.info("User entered age: " + age);

            Person person = new Person(name, age);
            logger.info("Created person: " + person);
            System.out.println(person);

        } catch (Exception e) {
            logger.severe("Error occurred: " + e.getMessage());
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
