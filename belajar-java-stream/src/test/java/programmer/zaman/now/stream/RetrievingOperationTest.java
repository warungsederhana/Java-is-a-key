package programmer.zaman.now.stream;

import org.junit.jupiter.api.Test;

import java.util.List;

public class RetrievingOperationTest {

  @Test
  void testLimit() {
    List.of("Eko", "Kurniawan", "Khannedy", "Budi", "Joko").stream()
        .limit(2)
        .forEach(System.out::println);
  }

  @Test
  void testSkip() {
    List.of("Eko", "Kurniawan", "Khannedy", "Budi", "Joko").stream()
        .skip(2)
        .forEach(System.out::println);
  }

  @Test
  void testTakeWhile() {
    // takeWhile() method will take the element from the stream until the condition is met
    // if the condition is not met, the stream will stop
    // in this case, the stream will stop when the name length is greater than or equal to 5
    List.of("Eko", "Kurniawan", "Khannedy", "Budi", "Joko").stream()
        .takeWhile(name -> name.length() < 5)
        .forEach(System.out::println);
  }

  @Test
  void testDropWhile() {
    // dropWhile() method will drop the element from the stream until the condition is met
    // if the condition is not met, the stream will continue
    // in this case, the stream will drop the element until the name length is greater than or equal to 5
    List.of("Eko", "Kurniawan", "Khannedy", "Budi", "Joko").stream()
        .dropWhile(name -> name.length() < 5)
        .forEach(System.out::println);
  }

  @Test
  void testFindAny() {
    List.of("Eko", "Kurniawan", "Khannedy", "Budi", "Joko").stream()
        .findAny()
        .ifPresent(System.out::println);
  }

  @Test
  void testFindFirst() {
    List.of("Eko", "Kurniawan", "Khannedy", "Budi", "Joko").stream()
        .findFirst()
        .ifPresent(System.out::println);
  }
}
