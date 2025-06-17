package com.constructor;
class Rat{}
class Animal {}
class Dog extends Animal {}

public class InstanceOFExample {
    public static void main(String[] args) {
        Animal animal = new Dog(); // Dog object assigned to Animal reference
        Animal animal1 = null;
        
        

        // Check if 'animal' is an instance of Dog
        System.out.println(animal instanceof Dog); // Output: true

        // Check if 'animal' is an instance of Animal
        System.out.println(animal instanceof Animal); // Output: true

        // Check if 'animal1' is an instance of dog
        System.out.println(animal1 instanceof Dog);//false
     
    }
}