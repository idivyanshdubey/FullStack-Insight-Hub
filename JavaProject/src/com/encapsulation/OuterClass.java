package com.encapsulation;
public class OuterClass {
    private String name = "John";

    class InnerClass {//making inner class meaning class within another class 
        public void printName() {//making method
            System.out.println("Name (using inner class): " + name);//printing private fields using inner clASS
        }
    }

    public static void main(String[] args) {
        OuterClass outer = new OuterClass();//MAKING INSTANCE OF OUTER CLASS
        OuterClass.InnerClass inner = outer.new InnerClass();//MAKING INSTANCE OF INNER CLSS USING OUTER CLASS
        inner.printName();//CALLING NAME METHOD
    }
}

