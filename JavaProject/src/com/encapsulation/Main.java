package com.encapsulation;


 	class Person {
 		
	    private String name; // Private field(because this is private we are using getter and setters to access this field

	    public String getName() { // Getter
	        return name;
	    }

	    public void setName(String name) { // Setter
	        this.name = name;
	    }
	}

	public class Main {
	    public static void main(String[] args) {
	        Person person = new Person();
	        person.setName("John");
	        System.out.println("Name: " + person.getName());
	    }
	}


//there are other ways also to access the private fields like inner class,serialization or within same class etc.