package com.enum1;

 enum TrafficLight {//we are not making it public because then we have to make new file because in same class there should be one public method
    RED, YELLOW, GREEN //making constant variables
}

public class Exmp {//entry

    public static void main(String[] args) {//main point
        TrafficLight currentSignal = TrafficLight.RED;//initializes the currentsignals with red value 
        displaySignalMessage(currentSignal);//calling method passing currentsignals

        currentSignal = TrafficLight.YELLOW;
        displaySignalMessage(currentSignal);

        currentSignal = TrafficLight.GREEN;
        displaySignalMessage(currentSignal);
    }

    public static void displaySignalMessage(TrafficLight signal) {//making method passing argument
        switch (signal) {
            case RED:
                System.out.println("Stop!");
                break;
            case YELLOW:
                System.out.println("Get ready to stop.");
                break;
            case GREEN:
                System.out.println("Go!");
                break;
        }
    }
}
