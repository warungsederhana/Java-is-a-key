package programmerzamannow.spring.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import programmerzamannow.spring.core.data.Foo;

public class DependsOnTest {
  private ApplicationContext context;

  @BeforeEach
  void setUp() {
    context = new AnnotationConfigApplicationContext(DependsOnConfiguration.class);
  }

  @Test
  void testDependsOn() {
    // run then check the console output
  }

  @Test
  void testDependsOnWithLazy() {
    // run then check the console output
    Foo foo = context.getBean(Foo.class);
  }
}
