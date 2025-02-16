package programmerzamannow.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SmallFileTest {

  @Test
  void writeSmallFile() {
    Path path1 = Path.of("small1.txt");
    byte[] bytes = "Hello World".getBytes();

    Path path2 = Path.of("small2.txt");
    byte[] bytes2 = "Hello World 2".getBytes();

    try {
      Files.write(path1, bytes);
      Files.write(path2, bytes2);

      Assertions.assertTrue(Files.exists(path1));
      Assertions.assertTrue(Files.exists(path2));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void readSmallFile() {
    Path path1 = Path.of("small1.txt");
    Path path2 = Path.of("small2.txt");


    try {
      byte[] bytes = Files.readAllBytes(path1);
      byte[] bytes2 = Files.readAllBytes(path2);

      Assertions.assertEquals("Hello World", new String(bytes));
      Assertions.assertEquals("Hello World 2", new String(bytes2));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
