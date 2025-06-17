package com.constructor;

class Student {
    String name;
    int age;

    // Constructor 1: Default constructor
    Student() {
        this("Unknown", 0); // Calls Constructor 2
        System.out.println("Default constructor called");
    }

    // Constructor 2: Parameterized constructor
    Student(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("Parameterized constructor called");
    }

    void displayInfo() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

public class ThisExample1 {
    public static void main(String[] args) {
        Student student1 = new Student(); // Calls default constructor
        student1.displayInfo();

//        Student student2 = new Student("Alice", 20); // Calls parameterized constructor
//        student2.displayInfo();
    }
}
//parameterized call,default call,this()