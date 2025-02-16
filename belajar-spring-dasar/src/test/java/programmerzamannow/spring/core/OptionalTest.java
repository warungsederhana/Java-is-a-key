package programmerzamannow.spring.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import programmerzamannow.spring.core.data.Foo;
import programmerzamannow.spring.core.data.FooBar;

public class OptionalTest {

  private ConfigurableApplicationContext context;

  @BeforeEach
  void setUp() {
    context = new AnnotationConfigApplicationContext(OptionalConfiguration.class);
    context.registerShutdownHook();
  }

  @Test
  void testScan() {
    Foo foo = context.getBean(Foo.class);
    FooBar fooBar = context.getBean(FooBar.class);

    Assertions.assertNull(fooBar.getBar());
    Assertions.assertSame(foo, fooBar.getFoo());
  }
}
