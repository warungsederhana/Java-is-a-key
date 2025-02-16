package programmerzamannow.spring.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import programmerzamannow.spring.core.data.Bar;
import programmerzamannow.spring.core.data.Foo;
import programmerzamannow.spring.core.data.FooBar;

public class DependencyInjectionTest {

  private ApplicationContext context;

  @BeforeEach
  void setUp() {
    context = new AnnotationConfigApplicationContext(DependencyInjectionConfiguration.class);
  }

  @Test
  void testNoDependencyInjection() {
    // No dependency injection
    Foo foo = new Foo();
    Bar bar = new Bar();
    FooBar fooBar = new FooBar(foo, bar);

    Assertions.assertSame(foo, fooBar.getFoo());
    Assertions.assertSame(bar, fooBar.getBar());
  }

  @Test
  void testWithDependencyInjection() {
    // With dependency injection
    Foo foo = context.getBean(Foo.class);
    Bar bar = context.getBean(Bar.class);
    FooBar fooBar = context.getBean(FooBar.class);

    Assertions.assertNotNull(fooBar);
    Assertions.assertNotNull(fooBar.getFoo());
    Assertions.assertNotNull(fooBar.getBar());
    Assertions.assertNotSame(foo, fooBar.getFoo());
    Assertions.assertSame(bar, fooBar.getBar());
  }
}
