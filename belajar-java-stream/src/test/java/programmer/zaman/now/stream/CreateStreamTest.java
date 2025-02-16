package programmer.zaman.now.stream;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class CreateStreamTest {

  @Test
  void testCreateEmptyOrSingleStream() {

    Stream<String> emptyStream = Stream.empty();
    emptyStream.forEach(value -> {
      System.out.println(value);
    });

    Stream<String> singleStream = Stream.of("Eko");
    singleStream.forEach(value -> {
      System.out.println(value);
    });

    String data = null;
    Stream<String> emptyOrNotStream = Stream.ofNullable(data);
    emptyOrNotStream.forEach(value -> {
      System.out.println(value);
    });

  }

  @Test
  void testCreateStreamFromArray() {

    Stream<String> arrayStream = Stream.of("Eko", "Kurniawan", "Khannedy");
    Stream<Integer> intStream = Stream.of(1, 2, 3, 4, 5);

    String[] names = new String[] {
      "Eko", "Kurniawan", "Khannedy"
    };

    Stream<String> namesStream = Arrays.stream(names);

    arrayStream.forEach(value -> System.out.println(value));

    intStream.forEach(System.out::println);

    namesStream.forEach(value -> {
      System.out.println(value);
    });

  }

  @Test
  void testCreateStreamFromCollection() {

    Collection<String> collection = List.of("Eko", "Kurniawan", "Khannedy");
    Stream<String> collectionStream = collection.stream();
    collectionStream.forEach(System.out::println);
//    collectionStream.forEach(System.out::println); // IllegalStateException: stream has already been operated upon or closed

  }

  @Test
  void testCreateInfiniteStream() {

    // Stream<String> stream = Stream.generate(() -> "Eko"); //infinite stream
    Stream<String> stream = Stream.generate(() -> "Eko").limit(10);
    stream.forEach(System.out::println);

    Stream<Integer> iterate = Stream.iterate(1, value -> value + 1).limit(10_000);
    iterate.forEach(System.out::println);
  }
}
