package classes;

import java.util.Base64;

public class Base64App {
  public static void main(String[] args) {
    String original = "Charisto Marc Gybran";

    String encoded = Base64.getEncoder().encodeToString(original.getBytes());
    System.out.println(encoded);

    byte[] bytes = Base64.getDecoder().decode(encoded);
    String result = new String(bytes);
    System.out.println(result);
  }
}
