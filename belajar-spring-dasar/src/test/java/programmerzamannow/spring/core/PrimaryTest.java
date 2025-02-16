package programmerzamannow.spring.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import programmerzamannow.spring.core.data.Foo;

public class PrimaryTest {
  private ApplicationContext applicationContext;

  @BeforeEach
  void setUp() {
    applicationContext = new AnnotationConfigApplicationContext(PrimaryConfiguration.class);
  }

  @Test
  void testGetPrimary() {
    Foo foo = applicationContext.getBean(Foo.class);
    Assertions.assertNotNull(foo);

    Foo foo1 = applicationContext.getBean("foo1", Foo.class);
    Foo foo2 = applicationContext.getBean("foo2", Foo.class);

    Assertions.assertNotNull(foo1);
    Assertions.assertNotNull(foo2);
    Assertions.assertSame(foo, foo1);
  }
}
