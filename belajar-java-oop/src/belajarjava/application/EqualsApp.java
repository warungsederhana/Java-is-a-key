package belajarjava.application;

public class EqualsApp {
    public static void main(String[] args) {
        String first = "Eko";
        first = first + " " + "Khannedy";
        System.out.println(first);

        String second = "Eko Khannedy";
        System.out.println(second);

        System.out.println(first == second);

        String third = "Eko Khannedy";

        System.out.println(second == third);

        System.out.println(first.equals(second));
        System.out.println(second.equals(third));

    }
}
