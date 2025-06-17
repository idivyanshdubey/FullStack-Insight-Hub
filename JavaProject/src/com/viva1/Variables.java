package com.viva1;

public class Variables {
	
		
	    static int sharedVar = 10;//common for all
	    int instanceVar = 20;//return above method and called through class object
	    private int instance = 30;

	    void display() {
	        int localVar = 30;
	        sharedVar=1;
	        System.out.println(localVar);
	    }
	    public int getInsatance() {//for private
	    	return instance;
	    }
	

}
