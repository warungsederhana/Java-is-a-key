package belajarjava.application;

//import belajarjava.data.Application;
import static belajarjava.data.Application.PROCESSORS;
//import belajarjava.data.Constant;
import static belajarjava.data.Constant.*;
import belajarjava.data.Country;
import belajarjava.util.MathUtils;

public class StaticApp {
    public static void main(String[] args) {

        System.out.println(APPLICATION);
        System.out.println(VERSION);
        System.out.println(LANGUAGE);

        System.out.println(MathUtils.sum(1, 1, 1, 1, 1));

        Country.City city= new Country.City();
        city.setName("Jakarta");
        System.out.println(city.getName());

        System.out.println(PROCESSORS);
    }
}
