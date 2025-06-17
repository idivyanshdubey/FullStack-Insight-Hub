package com.toString; //package

import com.loggerutilities.LoggerUtility; //import logger utility
import java.util.logging.Logger;
import java.util.logging.Level;

class Person1 {
    String name;
    int age;

    // Constructor
    Person1(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Override the equals() method
    @Override //if we override equals method we can compare the actual content of the object
    public boolean equals(Object obj) {
        // Check if the objects are the same reference
        if (this == obj) {
            return true;
        }

        // Check if the passed object is null or of a different type
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        // Compare the fields of the objects
        Person1 person = (Person1) obj;
        return this.age == person.age && this.name.equals(person.name);
    }
}

public class EqualsMethodExample {
    private static final Logger logger = LoggerUtility.getLogger(); //get logger from LoggerUtility

    public static void main(String[] args) {
        // Create two Person objects
        Person1 person1 = new Person1("Alice", 25);
        Person1 person2 = new Person1("Alice", 25);
        Person1 person3 = new Person1("Bob", 30);

        // Compare objects using equals()
        boolean result1 = person1.equals(person2); // true
        boolean result2 = person1.equals(person3); // false

        logger.info("Comparing person1 and person2: " + result1);
        logger.info("Comparing person1 and person3: " + result2);

        System.out.println("person1 equals person2: " + result1);
        System.out.println("person1 equals person3: " + result2);
    }
}
