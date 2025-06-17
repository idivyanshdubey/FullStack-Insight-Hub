package com.constructor;
class Example {
    // Instance block
    {
        System.out.println("Instance block executed");
    }

    // Constructor
    Example() {
        System.out.println("Constructor executed");
    }
    
    {
    	System.out.println("Hello");
    }
    
    
    
}

public class InstanceBlock {
    public static void main(String[] args) {
        Example obj1 = new Example(); // Instance block runs before the constructor
        Example obj2 = new Example(); // Instance block runs again for the new object
    }
}