package collection;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ImmutableMapApp {
  public static void main(String[] args) {

    Map<String, String> empty = Collections.emptyMap();
    Map<String, String> singleton = Collections.singletonMap("name", "Eko");

    Map<String, String> mutable = new HashMap<>();
    mutable.put("name", "Eko");
    Map<String, String> immutable = Collections.unmodifiableMap(mutable);

    Map<String, String> name = Map.of(
        "first", "Eko",
        "middle", "Kurniawan",
        "last", "Khannedy"
    );

    // name.put("first", "Budi"); // Error

  }
}
