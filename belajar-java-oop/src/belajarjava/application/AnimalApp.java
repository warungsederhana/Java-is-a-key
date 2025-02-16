package belajarjava.application;

import belajarjava.annotation.Fancy;
import belajarjava.data.Animal;
import belajarjava.data.Cat;

@Fancy(name = "Animal App", tags = {"application", "java"})
public class AnimalApp {
    public static void main(String[] args) {
        Animal animal = new Cat();
        animal.name = "Puss";
        animal.run();
    }
}
