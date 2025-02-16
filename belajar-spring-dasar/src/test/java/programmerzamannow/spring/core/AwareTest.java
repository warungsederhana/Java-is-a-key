package programmerzamannow.spring.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import programmerzamannow.spring.core.service.AuthService;

public class AwareTest {

  @Configuration
  @Import ({
      AuthService.class,
  })
  public static class TestConfiguration {}

  private ConfigurableApplicationContext context;

  @BeforeEach
  void setUp() {
    context = new AnnotationConfigApplicationContext(TestConfiguration.class);
    context.registerShutdownHook();
  }

  @Test
  void testAware() {
    AuthService authService = context.getBean(AuthService.class);
    System.out.println(authService.getBeanName());
    System.out.println(authService.getApplicationContext());

    Assertions.assertNotNull(authService.getBeanName());
    Assertions.assertNotNull(authService.getApplicationContext());
    Assertions.assertSame("programmerzamannow.spring.core.service.AuthService", authService.getBeanName());
  }
}
