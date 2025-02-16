package programmerzamannow.spring.core;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class LifeCycleTest {

  @Test
  void testLifeCycle() {
    // run then check the console output
    ApplicationContext context = new AnnotationConfigApplicationContext(LifeCycleConfiguration.class);

  }
}
