package com.iostream;
//
//
//	import java.io.FileReader;
//	import java.io.IOException;
//
//	public class FileReader1 {
//	    public static void main(String[] args) {
//	        try (FileReader reader = new FileReader("C:\\\\\\\\Users\\\\\\\\10827307\\\\\\\\eclipse-workspace\\\\\\\\Viva\\\\\\\\src\\\\\\\\com\\\\\\\\iostream\\\\\\\\example.txt")) {
//	            int data;
//	            while ((data = reader.read()) != -1) {
//	                System.out.print((char) data);
//	            }
//	        } catch (IOException e) {
//	            System.err.println("Error: " + e.getMessage());
//	        }
//	    }
//	}
//



import java.io.FileReader;
import java.io.IOException;

public class FileReader1 {
    public static void main(String[] args) {
//        if (args.length != 1) {
//            System.err.println("Usage: java FileReader1 <file-path>");//This block checks if exactly one command-line argument (the file path) is provided. 
//            //If not, it prints an error message to the standard error stream and exits the program.
//            return;
//        }

        String filePath = args[0];

        try (FileReader reader = new FileReader(filePath)) {
            int data;
            while ((data =  reader.read()) != -1) {//This block reads the file character by character. 
            	//The reader.read() method returns the next character as an integer.
                System.out.print((char) data);//type cast because readers.read()method return next character as integer that's why we are type casting it
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

