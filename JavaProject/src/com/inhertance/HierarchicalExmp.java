package com.inhertance;
//Parent class
class Parent111 {
 void displayParentMessage() {
     System.out.println("This is the parent class.");
 }
}

//Child class 1
class Child11 extends Parent111 {
 void displayChild1Message() {
     System.out.println("This is child class 1.");
 }
}

//Child class 2
class Child21 extends Parent111 {
 void displayChild2Message() {
     System.out.println("This is child class 2.");
 }
}

public class HierarchicalExmp {
 public static void main(String[] args) {
     // Create objects of child classes
     Child11 child1 = new Child11();
     Child21 child2 = new Child21();

     // Access parent class method
     child1.displayParentMessage();
     child2.displayParentMessage();

     // Access child class methods
     child1.displayChild1Message();
     child2.displayChild2Message();
 }
}