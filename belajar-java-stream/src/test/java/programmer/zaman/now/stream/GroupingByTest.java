package programmer.zaman.now.stream;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupingByTest {

  @Test
  void testGroupingBy() {
    Map<String, List<Integer>> collect = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        .collect(Collectors.groupingBy(number -> number > 5 ? "Besar" : "Kecil"));

    System.out.println(collect);
  }

  @Test
  void testGroupingBy2() {
    Map<String, List<String>> collect = Stream.of("Eko", "Kurniawawn", "Khannedy", "Eko", "Eko")
        .collect(Collectors.groupingBy(name -> name.length() > 4 ? "Panjang" : "Pendek"));

    System.out.println(collect);
  }

  @Test
  void testPartitioningBy() {
    Map<Boolean, List<Integer>> collect = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        .collect(Collectors.partitioningBy(number -> number > 5));

    System.out.println(collect);
  }
}
