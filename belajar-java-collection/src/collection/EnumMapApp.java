package collection;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

public class EnumMapApp {

  public static enum Level {
    FREE, STANDARD, PREMIUM, VIP
  }

  public static void main(String[] args) {
    Map<Level, String> map = new EnumMap<>(Level.class);
    map.put(Level.FREE, "Eko");
    map.put(Level.STANDARD, "Kurniawan");
    map.put(Level.PREMIUM, "Khannedy");
    map.put(Level.VIP, "Programmer");

    for(var key : map.keySet()) {
      System.out.println(map.get(key));
    }
  }
}
