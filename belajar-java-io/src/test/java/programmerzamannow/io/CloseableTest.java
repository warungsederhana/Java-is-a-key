package programmerzamannow.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class CloseableTest {

  @Test
  void testCloseIO() {
    Path path = Path.of("pom.xml");
    InputStream inputStream = null;

    try {
      inputStream = Files.newInputStream(path);

      // do something with inputStream
    } catch (IOException e) {
      Assertions.fail(e);
    } finally {
      if (inputStream != null) {
        try {
          inputStream.close();
        } catch (IOException e) {
          Assertions.fail(e);
        }
      }
    }
  }

  @Test
  void testCloseIOWithResource() {
    Path path = Path.of("pom.xml");

    try (InputStream inputStream = Files.newInputStream(path)) {
      // do something with inputStream
    } catch (IOException e) {
      Assertions.fail(e);
    }
  }
}
