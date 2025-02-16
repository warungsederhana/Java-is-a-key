package belajarjava.application;

import belajarjava.data.Category;

public class CategoryApp {
    public static void main(String[] args) {
        var category = new Category();
        category.setId("ID");
        System.out.println(category.getId());
        System.out.println(category.isExpensive());
    }
}
