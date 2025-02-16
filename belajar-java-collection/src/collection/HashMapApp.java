package collection;

import java.util.HashMap;
import java.util.Map;

public class HashMapApp {
  public static void main(String[] args) {

    Map<String, String> map = new HashMap<>();
    map.put("first", "Eko");
    map.put("middle", "Kurniawan");
    map.put("last", "Khannedy");

    for (var key : map.keySet()) {
      System.out.println(key);
    }

    for (var value : map.values()) {
      System.out.println(value);
    }

    System.out.println(map.get("first"));
    System.out.println(map.get("middle"));
    System.out.println(map.get("last"));

  }
}
