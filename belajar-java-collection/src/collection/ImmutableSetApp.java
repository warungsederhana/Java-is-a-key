package collection;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ImmutableSetApp {
  public static void main(String[] args) {

    Set<String> empty = Collections.emptySet();
    Set<String> one = Collections.singleton("Satu");
    Set<String> mutable =  new HashSet<>();
    mutable.add("Eko");
    mutable.add("Kurniawan");

    Set<String> immutable = Collections.unmodifiableSet(mutable);

    Set<String> set = Set.of("Eko", "Kurniawan", "Khannedy");
    // ERROR karena set adalah immutable
    // set.remove("Eko");

    for (var value : set) {
      System.out.println(value);
    }
  }
}
