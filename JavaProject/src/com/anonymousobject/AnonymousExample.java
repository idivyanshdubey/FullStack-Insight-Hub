package com.anonymousobject;

class Calculator {
    void multiply(int a, int b) {
        System.out.println("Product: " + (a * b));
    }
}

public class AnonymousExample {
    public static void main(String[] args) {
        // Anonymous object calling a method
        new Calculator().multiply(6, 7); // Object created, method executed, object discarded
    }
}
