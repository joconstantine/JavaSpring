package main.java.com.eazybytes.java8;

public interface Vehicle {

    public int getSpeed();

    public void applyBreak();

    public default void autoPilot() {
        System.out.println("I will help in driving without manual support");
    }

    public static void sayHello() {
        System.out.println("Hi, this is your favourite car");
    }
}
