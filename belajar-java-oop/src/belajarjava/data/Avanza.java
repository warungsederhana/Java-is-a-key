package belajarjava.data;

public class Avanza implements Car {
    public void drive() {
        System.out.println("Avanza drive");
    }

    public boolean isMaintenance() {
        return false;
    }

    public String getBrand() {
        return "Toyota";
    }

    public int getTire() {
        return 4;
    }
}
