package programmerzamannow.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManipulationTest {

  @Test
  void testManipulationFile() {
    Path path = Path.of("file.txt");

    try {
      Files.createFile(path);
      Assertions.assertTrue(Files.exists(path));

      Files.delete(path);
      Assertions.assertFalse(Files.exists(path));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void testManipulationDirectory() {
    Path path = Path.of("contoh");

    try {
      Files.createDirectory(path);
      Assertions.assertTrue(Files.isDirectory(path));
      Assertions.assertTrue(Files.exists(path));

      Files.delete(path);
      Assertions.assertFalse(Files.exists(path));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
