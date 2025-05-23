package programmer.zaman.now.stream;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

public class TransformationOperationTest {

  @Test
  void testMap() {

    // map() digunakan untuk mentransformasi setiap elemen menjadi elemen baru
    // map() mengubah dari nama menjadi nama yang diubah menjadi huruf besar
    // kemudian stream diubah menjadi panjang dari nama yang sudah diubah menjadi huruf besar
    // Stream<String> -> Stream<String> -> Stream<Integer>
    List.of("Eko", "Kurniawan", "Khannedy").stream()
        .map(name -> name.toUpperCase())
        .map(upper -> upper.length())
        .forEach(length -> System.out.println(length));
  }

  @Test
  void testFlatMap() {

    List.of("Eko", "Kurniawan", "Khannedy").stream()
        .map(name -> name.toUpperCase())
        .flatMap(upper -> Stream.of(upper, upper.length()))
        .flatMap(value -> Stream.of(value, value, value))
        .forEach(length -> System.out.println(length));
  }
}
