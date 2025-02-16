package programmer.zaman.now.stream;

import org.junit.jupiter.api.Test;

import java.util.List;

public class CheckOperationTest {

  @Test
  void testAnyMatch() {
    boolean isMatch = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).stream()
        .anyMatch(number -> number > 5);

    System.out.println(isMatch);
  }

  @Test
  void testAllMatch() {
    boolean isMatch = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).stream()
        .allMatch(number -> number > 5);

    System.out.println(isMatch);
  }

  @Test
  void testNoneMatch() {
    boolean isMatch = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).stream()
        .noneMatch(number -> number > 12);

    System.out.println(isMatch);
  }
}
