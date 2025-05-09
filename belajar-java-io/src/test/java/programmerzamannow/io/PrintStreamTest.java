package programmerzamannow.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class PrintStreamTest {
  @Test
  void console() {
    PrintStream out = System.out;

    out.println("Hello World");
    out.println("Hello World");
    out.println("Hello World");
    out.println("Hello World");
  }

  @Test
  void printFile() {
    Path path = Path.of("print.txt");

    try (OutputStream outputStream = Files.newOutputStream(path);
         PrintStream stream = new PrintStream(outputStream)) {

      stream.println("Hello World");
      stream.println("Hello World");
      stream.println("Hello World");
    } catch (IOException e) {
      Assertions.fail(e);
    }
  }
}
