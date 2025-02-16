package programmer.zaman.now.stream;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsTest {

  Stream<String> getStream() {
    return Stream.of("Eko", "Kurniawan", "Khannedy");
  }

  @Test
  void testCollection() {
    Set<String> setString = getStream().collect(Collectors.toSet());
    System.out.println(setString);
    Set<String> immutableSetString = getStream().collect(Collectors.toUnmodifiableSet());
    System.out.println(immutableSetString);

    List<String> listString = getStream().collect(Collectors.toList());
    System.out.println(listString);
    List<String> immutableListString = getStream().collect(Collectors.toUnmodifiableList());
    System.out.println(immutableListString);
  }

  @Test
  void testMap() {
    Map<String, Integer> map = getStream().collect(Collectors.toMap(
        name -> name,
        name -> name.length()
    ));

    System.out.println(map);
  }
}
