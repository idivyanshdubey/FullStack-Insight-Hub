package com.thread;

//class SharedResource {
//    volatile boolean flag = false;
//
//    void updateFlag() {
//        flag = true; // Update the flag
//        System.out.println("Flag updated to true");
//    }
//
//    void checkFlag() {
//        while (!flag) {
//            // Busy-wait until flag is true
//        }
//        System.out.println("Flag detected as true");
//    }
//}
//
//public class VolatileExmp {
//    public static void main(String[] args) {
//        SharedResource resource = new SharedResource();
//
//        // Thread to update the flag
//        new Thread(resource::updateFlag).start();
//
//        // Thread to check the flag
//        new Thread(resource::checkFlag).start();
//    }
//}