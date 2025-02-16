package programmer.zaman.now.stream;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

public class CreateStreamBuilderTest {
  @Test
  void testCreateStreamBuilder() {

    Stream.Builder<String> stringBuilder = Stream.builder();
    stringBuilder.accept("Eko");                            // return void
    stringBuilder.add("Kurniawan").add("Khannedy");            // return Stream.Builder

    Stream<String> stringStream = stringBuilder.build();

    stringStream.forEach(System.out::println);
  }

  @Test
  void testCreateStreamBuilderSimplify() {
    Stream<Object> stream = Stream.builder()
        .add("Eko").add("Kurniawan").add("Khannedy").build();

    stream.forEach(System.out::println);
  }
}
