package programmerzamannow.spring.core.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import programmerzamannow.spring.core.data.Foo;

public class WithoutSpringBootTest {

  private ConfigurableApplicationContext context;

  @BeforeEach
  void setUp() {
    context = new AnnotationConfigApplicationContext(FooApplication.class);
    context.registerShutdownHook();
  }

  @Test
  void testWithoutSpringBoot() {
    Foo foo = context.getBean(Foo.class);
    System.out.println(foo);
  }
}
