package com.enum1;
 enum Status {
    SUCCESS(20), ERROR(30), PENDING(40);
	 int  i= 0;
	 Status(){}

	Status(int i) {
		this.i = i;
	}
	public int getCode() {
		return i;
	
	}
}

public class EnumExample1 {
    public static void main(String[] args) {
        // Get all enum constants
    	//Status e = Status.SUCCESS;
        for (Status status : Status.values()) {
            System.out.println("Enum: " + status + ", Ordinal: " + status.ordinal());
        }

        // Get specific enum constant by name
        Status status = Status.valueOf("SUCCESS");
        System.out.println("Retrieved Enum: " + status);//+ "value of success " +e.getCode());

        // Compare two enums
//        System.out.println("Comparison: " + Status.SUCCESS.compareTo(Status.ERROR));
//
//        // Get declaring class
//        System.out.println("Declaring Class: " + Status.SUCCESS.getDeclaringClass());
    }
}
