package com.annotation2;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

// Annotation is retained only in source code
@Retention(RetentionPolicy.SOURCE)//exist only in source code and are discarded during compilation. we cannot use retention to ptint the value of interface method because it does not exist in the bytecode
@Target(ElementType.METHOD)
@interface SourceAnnotation {
   String info() default "This is a source annotation";//only public and abstract are allowed because it cannot be accessed if other access will be there
}
class Emp{
	public void dis() {
		System.out.println("playing");
	}
	
	 @SourceAnnotation(info = "Method for testing")
	    public void test() {
	        System.out.println("Executing test method.");
	    
	
}

 //Applying annotation
public class SourceExample {
	public static void main(String[] args) {
		Emp e = new Emp();
		e.test();
		e.dis();
	}
   
    }
}
//Commonly used for code documentation or compiler hints. not accessible at run  time . not available in the class file useful in documentation eg:@override and @supresswarning








