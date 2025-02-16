package collection;

import java.util.Collection;
import java.util.Collections;
import java.util.NavigableMap;
import java.util.TreeMap;

public class NavigableMapApp {
  public static void main(String[] args) {

    NavigableMap<String, String> map = new TreeMap<>();

    map.put("Eko", "Eko Kurniawan");
    map.put("Budi", "Budi Oktaviyan");
    map.put("Joko", "Joko Susilo");

    for (var key : map.keySet()) {
      System.out.println(key);
    }

    System.out.println("KEY di bawah 'eko': " + map.lowerKey("Eko"));
    System.out.println("KEY di atas 'eko : " + map.higherKey("Eko"));
    System.out.println("KEY di bawah atau sama dengan 'eko' : " + map.floorKey("Eko"));
    System.out.println("KEY di atas atau sama dengan 'eko' : " + map.ceilingKey("Eko"));

    NavigableMap<String, String> mapDesc = map.descendingMap();
    for (var key : mapDesc.keySet()) {
      System.out.println(key);
    }

    // IMMUATABLE
    System.out.println("=== IMMUATABLE");

    NavigableMap<String, String> empty = Collections.emptyNavigableMap();
    NavigableMap<String, String> immutable = Collections.unmodifiableNavigableMap(mapDesc);

    // immutable.put("Rudi", "Rudi"); // ERROR: UnsupportedOperationException
  }
}
