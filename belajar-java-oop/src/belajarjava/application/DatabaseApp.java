package belajarjava.application;

import belajarjava.error.DatabaseError;

public class DatabaseApp {
  public static void main(String[] args) {
    connectDatabase("eko", "rahasia");
    System.out.println("Sukses connect ke database");
  }

  public static void connectDatabase(String username, String password) {
    if (username == null || password == null) {
      throw new DatabaseError("Tidak bisa connect ke database");
    }

  }
}
