package programmerzamannow.spring.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import programmerzamannow.spring.core.data.Foo;

public class DuplicateTest {

  @Test
  void testDuplicate() {
    ApplicationContext context = new AnnotationConfigApplicationContext(DuplicateConfiguration.class);
    Assertions.assertNotNull(context);

    Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> {
      // Ambiguity between foo1 and foo2
      // Error if out of this assertion block
      Foo foo = context.getBean(Foo.class);
    });
  }

  @Test
  void testAccessDuplicate() {
    // Accessing duplicate bean

    ApplicationContext context = new AnnotationConfigApplicationContext(DuplicateConfiguration.class);
    Assertions.assertNotNull(context);

    Foo foo1 = context.getBean("foo1", Foo.class);
    Foo foo2 = context.getBean("foo2", Foo.class);

    Assertions.assertNotNull(foo1);
    Assertions.assertNotNull(foo2);
    Assertions.assertNotSame(foo1, foo2);
  }
}
