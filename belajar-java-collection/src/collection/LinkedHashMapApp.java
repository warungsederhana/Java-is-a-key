package collection;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class LinkedHashMapApp {
  public static void main(String[] args) {

    Map<String, String> map = new LinkedHashMap<>();

    map.put("first", "Eko");
    map.put("middle", "Kurniawan");
    map.put("last", "Khannedy");

    Set<String> keys = map.keySet();

    for (var key : keys) {
      System.out.println(key);
    }

  }
}
