package lambda.app;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class MapForEachApp {
  public static void main(String[] args) {

    Map<String, String> map = new HashMap<>();
    map.put("first_name", "Eko");
    map.put("middle_name", "Kurniawan");
    map.put("last_name", "Khannedy");

    // for loop
    for (var entry : map.entrySet()) {
      System.out.println(entry.getKey() + " : " + entry.getValue());
    }

    // forEach dengan anonymous class
    map.forEach(new BiConsumer<String, String>() {
      @Override
      public void accept(String s, String s2) {
        System.out.println(s + " : " + s2);
      }
    });

    // forEach dengan lambda
    map.forEach((key, value) -> System.out.println(key + " : " + value));
  }
}
