package com.thread;


	class MyThread11 extends Thread {
	    public void run() {
	        System.out.println("Thread is in running state.");
	    }
	}

	public class ThreadLifecycleExample {
	    public static void main(String[] args) throws InterruptedException {
	        Thread thread = new MyThread11(); // New state
	        thread.start(); // Moves to Runnable and Running
	        thread.join(); // Waits for thread to finish
	        System.out.println("Thread has terminated.");
	    }
	}


