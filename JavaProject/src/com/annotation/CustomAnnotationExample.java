package com.annotation;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.Scanner;
import java.util.logging.*;

// Define custom annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface MyAnnotation {
    String value();
}

public class CustomAnnotationExample {
    private static final Logger logger = Logger.getLogger(CustomAnnotationExample.class.getName());

    static {
        setupLogger();
    }

    @MyAnnotation(value = "Custom Message")
    public void display() {
        System.out.println("Custom Annotation applied.");
        logger.info("display() method executed.");
    }

    public static void main(String[] args) {
        logger.info("Program started.");
        Scanner scanner = new Scanner(System.in);
        CustomAnnotationExample obj = new CustomAnnotationExample();

        System.out.print("Enter the method name to invoke (e.g., display): ");
        String methodName = scanner.nextLine();

        try {
            Method method = obj.getClass().getMethod(methodName);
            logger.info("Method '" + methodName + "' found.");

            if (method.isAnnotationPresent(MyAnnotation.class)) {
                MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
                System.out.println("Annotation value: " + annotation.value());
                logger.info("Annotation value accessed: " + annotation.value());
            }

            method.invoke(obj);
        } catch (NoSuchMethodException e) {
            System.out.println("No such method found.");
            logger.warning("No such method: " + methodName);
        } catch (Exception e) {
            System.out.println("Error invoking method: " + e.getMessage());
            logger.severe("Error invoking method: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void setupLogger() {
        try {
            LogManager.getLogManager().reset();
            FileHandler fh = new FileHandler("core_java_programs.log", true);
            fh.setFormatter(new SimpleFormatter());
            Logger rootLogger = Logger.getLogger("");
            rootLogger.addHandler(fh);
            rootLogger.setLevel(Level.INFO);
        } catch (Exception e) {
            System.err.println("Logger setup failed: " + e.getMessage());
        }
    }
}
