package lambda.app;

import java.util.function.Supplier;

public class LambdaLazyApp {
  public static void main(String[] args) {
    System.out.println("=== EAGER");
    testScore(40, getName());

    System.out.println("=== LAZY");
    lazyTestScore(40, () -> getName()); // ubah score menjadi > 80
  }

  // eager
  public static void testScore(int score, String name) {
    if (score > 80) {
      System.out.println("Selamat " + name + ", Anda Lulus");
    } else {
      System.out.println("Coba lagi tahun depan");
    }
  }

  // lazy
  public static void lazyTestScore(int score, Supplier<String> name) {
    if (score > 80) {
      System.out.println("Selamat " + name.get() + ", Anda Lulus");
    } else {
      System.out.println("Coba lagi tahun depan");
    }
  }

  public static String getName() {
    System.out.println("Method getName() dipanggil");
    return "Eko";
  }
}
