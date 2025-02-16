package belajarjava.data;

public class Bus implements Car {
    public void drive() {
        System.out.println("Bus drive");
    }

    public boolean isMaintenance() {
        return false;
    }

    public String getBrand() {
        return "Hino";
    }

    public int getTire() {
        return 6;
    }

    public boolean isBig() {
        return true;
    }
}
