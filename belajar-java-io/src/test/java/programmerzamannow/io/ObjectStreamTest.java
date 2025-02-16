package programmerzamannow.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class ObjectStreamTest {

  @Test
  void savePerson() {
    Person person = new Person("Eko", "Eko Kurniawan Khannedy");
    Path path = Path.of("eko.person");

    try (OutputStream stream = Files.newOutputStream(path);
         ObjectOutputStream objectStream = new ObjectOutputStream(stream)) {

      objectStream.writeObject(person);
      objectStream.flush();
    } catch (IOException e) {
      Assertions.fail(e);
    }
  }

  @Test
  void getPerson() {
    Path path = Path.of("eko.person");

    try (InputStream stream = Files.newInputStream(path);
         ObjectInputStream objectStream = new ObjectInputStream(stream)) {

      Person person = (Person) objectStream.readObject();

      Assertions.assertEquals("Eko", person.getId());
      Assertions.assertEquals("Eko Kurniawan Khannedy", person.getName());
    } catch (IOException | ClassNotFoundException e) {
      Assertions.fail(e);

    }
  }
}
