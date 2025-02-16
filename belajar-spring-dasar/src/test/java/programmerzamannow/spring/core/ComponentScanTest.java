package programmerzamannow.spring.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import programmerzamannow.spring.core.data.Bar;
import programmerzamannow.spring.core.data.Foo;

public class ComponentScanTest {

  private ConfigurableApplicationContext context;

  @BeforeEach
  void setUp() {
    context = new AnnotationConfigApplicationContext(ScanConfiguration.class);
    context.registerShutdownHook();
  }

  @Test
  void testScan() {
    Foo foo = context.getBean(Foo.class);
    Bar bar = context.getBean(Bar.class);

    System.out.println(foo);
    System.out.println(bar);
  }
}
