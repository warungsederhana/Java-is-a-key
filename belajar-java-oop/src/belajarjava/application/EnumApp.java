package belajarjava.application;

import belajarjava.belajarenum.Level;
import belajarjava.data.Customer;

public class EnumApp {
    public static void main(String[] args) {

        Customer customer = new Customer();
        customer.setName("Eko");
        customer.setLevel(Level.PREMIUM);

        System.out.println(customer.getName());
        System.out.println(customer.getLevel());
        System.out.println(customer.getLevel().getDescription());

        String levelName = Level.VIP.name();
        System.out.println(levelName);

        Level level = Level.valueOf("PREMIUM");
        System.out.println(level);

        Level[] values = Level.values();
        for (var value : values) {
            System.out.println(value);
        }
    }
}
