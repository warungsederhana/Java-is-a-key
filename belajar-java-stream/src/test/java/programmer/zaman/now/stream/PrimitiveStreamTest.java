package programmer.zaman.now.stream;

import org.junit.jupiter.api.Test;

import java.util.OptionalDouble;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class PrimitiveStreamTest {

  @Test
  void testCreate(){
    IntStream intStream = IntStream.of(1, 2, 3, 4, 5);
    IntStream intStreamFromRange = IntStream.range(1, 100);

    intStream.forEach(System.out::println);
    intStreamFromRange.forEach(System.out::println);

    LongStream longStream = LongStream.of(1, 2, 3, 4, 5);
    DoubleStream doubleStream = DoubleStream.of(1.0, 2.0, 3.0, 4.0, 5.0);
  }

  @Test
  void testOperation() {
    IntStream intStream = IntStream.range(1, 10);
    IntStream intStream2 = IntStream.range(1, 10);

    OptionalDouble optionalDouble = intStream.average();
    optionalDouble.ifPresent(System.out::println);

    Stream<String> stringStream = intStream2.mapToObj(number -> "number: " + number);
    stringStream.forEach(System.out::println);
  }
}
