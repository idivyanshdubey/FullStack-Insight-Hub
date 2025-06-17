package com.conversion;
class Student {
    String name;
    int age;

    // Parameterized constructor
    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Copy constructor
    Student(Student student) {
        this.name = student.name;// if you not write this it will show null and zero
        this.age = student.age;
    }

    void displayInfo() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

public class CopyConstructor {
    public static void main(String[] args) {
        // Original object
        Student student1 = new Student("Alice", 20);
        student1.displayInfo();

        // Copying the object using the copy constructor
        Student student2 = new Student(student1);
        student2.displayInfo();
    }
}
