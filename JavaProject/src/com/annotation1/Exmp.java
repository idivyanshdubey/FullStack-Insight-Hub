package com.annotation1;




import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS) // Annotation is stored in class file but not at runtime
@Target(ElementType.TYPE) // Can be applied to classes and interfaces
@interface ClassInfo {
    String author() default "Unknown";
    String version();
}

// Applying annotation
@ClassInfo(author = "Bob", version = "1.0")
 class SampleClass {
    public void display() {
        System.out.println("This is SampleClass.");
    }
}



public class Exmp {
	public static void main(String[] args) {
		SampleClass s = new SampleClass();
		s.display();
		
	}

}
//but not for runtime reflection.when you do not want it qxpose it during runtime we  can use this 