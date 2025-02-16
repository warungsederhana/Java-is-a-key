package programmerzamannow.spring.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import programmerzamannow.spring.core.data.Foo;
import programmerzamannow.spring.core.processor.FooBeanFactoryPostProcessor;

public class BeanFactoryPostProcessorTest {

  @Component
  @Import({
    FooBeanFactoryPostProcessor.class
  })
  public static class TestConfiguration{}

  private ConfigurableApplicationContext context;

  @BeforeEach
  void setUp() {
    context = new AnnotationConfigApplicationContext(TestConfiguration.class);
    context.registerShutdownHook();
  }

  @Test
  void testBeanFactoryTestProcessor() {
    Foo foo = context.getBean(Foo.class);
    Assertions.assertNotNull(foo);
  }
}
