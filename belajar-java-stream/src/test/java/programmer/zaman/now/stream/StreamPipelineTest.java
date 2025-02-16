package programmer.zaman.now.stream;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

public class StreamPipelineTest {
  @Test
  void testCreateStreamPipeline() {

    // JARANG DIGUNAKAN
    List<String> list = List.of("Eko", "Kurniawan", "Khannedy");
    Stream<String> stream = list.stream();
    Stream<String> streamUpper = stream.map(name -> name.toUpperCase());
    Stream<String> streamMr = streamUpper.map(value -> "Mr. " + value);
    streamMr.forEach(System.out::println);

  }

  @Test
  void testCreateStreamPipelineOk() {
    List<String> list = List.of("Eko", "Kurniawan", "Khannedy");

    list.stream()
        .map(name -> name.toUpperCase())
        .map(value -> "Mr. " + value)
        .forEach(System.out::println);
  }
}
