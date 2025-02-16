package programmerzamannow.spring.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import programmerzamannow.spring.core.data.Car;
import programmerzamannow.spring.core.processor.IdGeneratorPostProcessor;

public class BeanPostProcessorTest {

  @Configuration
  @Import({
      Car.class,
      IdGeneratorPostProcessor.class
  })
  public static class TestConfiguration {
  }

  private ConfigurableApplicationContext context;

  @BeforeEach
  void setUp() {
    context = new AnnotationConfigApplicationContext(TestConfiguration.class);
    context.registerShutdownHook();
  }

  @Test
  void testCar() {
    Car car = context.getBean(Car.class);

    System.out.println(car.getId());
    Assertions.assertNotNull(car.getId());
  }
}
