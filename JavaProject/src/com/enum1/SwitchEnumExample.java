package com.enum1;

enum Day1 {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

public class SwitchEnumExample {
    public static void main(String[] args) {
        Day1 today = Day1.WEDNESDAY;

        // Using ordinal() to get the index of the current day
        System.out.println("Today is " + today + " and its ordinal value is " + today.ordinal());//return index

        // Using valueOf() to get the enum constant from a string
        Day1 dayFromString = Day1.valueOf("FRIDAY");//return same value 
        System.out.println("Day from string 'FRIDAY' is " + dayFromString);

        switch (today) {
            case MONDAY:
                System.out.println("Start of the work week!");
                break;
            case FRIDAY:
                System.out.println("Weekend is coming!");
                break;
            case SUNDAY:
                System.out.println("Rest day!");
                break;
            default:
                System.out.println("A regular day.");
        }
    }
}


//ordinal() at that particular index value  and values() is used return data valuesOf() to return value 