package programmer.zaman.now.stream;

import org.junit.jupiter.api.Test;

import java.util.List;

public class ForEachOperationTest {

  @Test
  void testPeek() {
    List.of("Eko", "Kurniawan", "Khannedy").stream()
        .peek(name -> System.out.println("Before Upper : " + name)) // not a terminal operation
        .map(String::toUpperCase)
        .peek(upper -> System.out.println("After Upper : " + upper)) // not a terminal operation
        .forEach(System.out::println);
  }
}
