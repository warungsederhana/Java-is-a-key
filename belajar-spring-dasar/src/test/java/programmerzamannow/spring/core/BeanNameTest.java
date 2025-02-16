package programmerzamannow.spring.core;

import org.junit.jupiter.api.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import programmerzamannow.spring.core.data.Foo;

public class BeanNameTest {

  private ApplicationContext applicationContext;

  @BeforeEach
  void setUp() {
    applicationContext = new AnnotationConfigApplicationContext(BeanNameConfiguration.class);
  }

  @Test
  void testBeanName() {
    Foo foo = applicationContext.getBean(Foo.class);
    Foo foo1 = applicationContext.getBean("fooFirst", Foo.class);
    Foo foo2 = applicationContext.getBean("fooSecond", Foo.class);

    Assertions.assertSame(foo, foo1);
    Assertions.assertNotNull(foo);
    Assertions.assertNotNull(foo1);
    Assertions.assertNotNull(foo2);
  }
}
