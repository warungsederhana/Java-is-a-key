package belajarjava.application;

import belajarjava.data.Avanza;
import belajarjava.data.Car;

public class CarApp {
    public static void main(String[] args) {
        Car car = new Avanza();
        System.out.println(car.getTire());
        car.drive();
    }
}
