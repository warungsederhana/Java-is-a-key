package belajarjava.application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadApp {
  public static void main(String[] args) {

    BufferedReader reader = null;

    System.out.println("=== Tanpa Try With Resources");
//  TANPA TRY WITH RESOURCES
    try {

      reader = new BufferedReader(new FileReader("README.md"));

      while (true) {
        String line = reader.readLine();
        if (line == null) {
          break;
        }
        System.out.println(line);
      }

    } catch (Throwable throwable) {
      System.out.println("Error membaca file: " + throwable.getMessage());
    } finally {

      if (reader != null) {
        try {
          reader.close();
          System.out.println("Sukses menutup resource");
        } catch (IOException e) {
          System.out.println("Error menutup resource: " + e.getMessage());
        }
      }
    }

    System.out.println("=== Dengan Try With Resources");

//  DENGAN TRY WITH RESOURCES
    try (BufferedReader reader2 = new BufferedReader(new FileReader("README.md"))) {

      while (true) {
        String line = reader2.readLine();
        if (line == null) {
          break;
        }
        System.out.println(line);
      }

    } catch (Throwable throwable) {
      System.out.println("Error membaca file: " + throwable.getMessage());
    }
  }
}
