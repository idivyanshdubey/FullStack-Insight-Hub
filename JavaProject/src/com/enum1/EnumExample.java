package com.enum1;


	enum Day {//ENUMERATION DATA TYPE
	    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
	}//we fixed set of constant

	public class EnumExample {
	    public static void main(String[] args) {
	        Day today = Day.FRIDAY;
	        System.out.println("Today is: " + today);
	    }
	}


//They are particularly useful when you have a variable that can only take one out of a small set of possible values. 