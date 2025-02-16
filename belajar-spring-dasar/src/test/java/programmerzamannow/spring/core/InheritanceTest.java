package programmerzamannow.spring.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import programmerzamannow.spring.core.service.IMerchantService;
import programmerzamannow.spring.core.service.MerchantService;

public class InheritanceTest {
  private ConfigurableApplicationContext context;

  @BeforeEach
  void setUp() {
    context = new AnnotationConfigApplicationContext(InheritanceConfiguration.class);
    context.registerShutdownHook();
  }

  @Test
  void testInheritance() {
    IMerchantService merchantService = context.getBean(IMerchantService.class);
    MerchantService merchantService2 = context.getBean(MerchantService.class);

    Assertions.assertSame(merchantService, merchantService2);
  }
}
