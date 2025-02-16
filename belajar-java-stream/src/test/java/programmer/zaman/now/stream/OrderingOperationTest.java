package programmer.zaman.now.stream;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

public class OrderingOperationTest {

  @Test
  void testSorted() {
    List.of("Eko", "Kurniawan", "Khannedy", "Budi", "Joko", "Zaman", "Now").stream()
        .sorted()
        .forEach(System.out::println);
  }

  @Test
  void testSortedWithComparator() {
    Comparator<String> reverseComparator = new Comparator<String>() {
      @Override
      public int compare(String o1, String o2) {
        return o2.compareTo(o1);
      }
    };

    List.of("Eko", "Kurniawan", "Khannedy", "Budi", "Joko", "Zaman", "Now").stream()
        .sorted(reverseComparator)
        .forEach(System.out::println);
  }
}
