package programmerzamannow.spring.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import programmerzamannow.spring.core.data.Bar;
import programmerzamannow.spring.core.data.Foo;

public class ScopeTest {

  @Test
  void testScope() {
    ApplicationContext context = new AnnotationConfigApplicationContext(ScopeConfiguration.class);

    Foo foo1 = context.getBean(Foo.class);
    Foo foo2 = context.getBean(Foo.class);
    Foo foo3 = context.getBean(Foo.class);

    Assertions.assertNotSame(foo1, foo2);
    Assertions.assertNotSame(foo1, foo3);
    Assertions.assertNotSame(foo2, foo3);
  }

  @Test
  void testDoubletonScope() {
    ApplicationContext context = new AnnotationConfigApplicationContext(ScopeConfiguration.class);

    Bar bar1 = context.getBean(Bar.class);
    Bar bar2 = context.getBean(Bar.class);
    Bar bar3 = context.getBean(Bar.class);
    Bar bar4 = context.getBean(Bar.class);

    Assertions.assertSame(bar1, bar3);
    Assertions.assertSame(bar2, bar4);
  }
}
