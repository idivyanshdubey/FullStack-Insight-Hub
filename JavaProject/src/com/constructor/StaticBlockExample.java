package com.constructor;
class StaticBlockExample {
    static int staticVariable;

    // Static block
    static {
        staticVariable = 42; // Initialize static variable
        System.out.println("Static block executed");
    }

    public static void main(String[] args) {
        System.out.println("Main method executed");
        System.out.println("Static Variable: " + staticVariable);
    }
}
