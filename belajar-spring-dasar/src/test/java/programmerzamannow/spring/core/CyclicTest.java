package programmerzamannow.spring.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import programmerzamannow.spring.core.data.cyclic.CyclicA;

public class CyclicTest {

  @Test
  void testCyclic() {
    // Error because of cyclic dependency
    // ApplicationContext context = new AnnotationConfigApplicationContext(CyclicConfiguration.class);

    Assertions.assertThrows(Throwable.class, () -> {
      ApplicationContext context = new AnnotationConfigApplicationContext(CyclicConfiguration.class);
    });
  }
}
