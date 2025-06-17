package com.ifelse;

public class Jumping {
	
	    public static void main(String[] args) {
	        for (int i = 1; i <= 5; i++) {
	            if (i == 3) {
	                continue; // Skip the rest of the code when i is 3
	            }
	            System.out.println("Count: " + i);
	        }
	    }
	}

//skip 3 part and print rest 