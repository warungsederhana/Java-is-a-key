package collection;

import java.util.Hashtable;
import java.util.Map;

public class HashTableApp {
  public static void main(String[] args) {

    Map<String, String> map = new Hashtable<>();

    map.put("first", "Eko");
    map.put("middle", "Kurniawan");
    map.put("last", "Khannedy");

    for (var key : map.keySet()) {
      System.out.println(map.get(key));
    }

  }
}
