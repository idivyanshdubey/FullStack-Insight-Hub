package com.conversion;

public class Conversion1 {
	public static void main(String[] args) {
		double decimal = 99.99;
         int num = (int) decimal; // Explicit casting(we trying to make it into smaller data types) that's why we have to explicitly write in which data type we have to convert it
         double num1 = 10.8;
         float num2 = (float) num1;
         System.out.println(num2);
         
		System.out.println(num);
		
	}
	
	

}
