package programmerzamannow.spring.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import programmerzamannow.spring.core.client.PaymentGatewayClient;

public class FactoryBeanTest {

  private ConfigurableApplicationContext context;

  @BeforeEach
  void setUp() {
    context = new AnnotationConfigApplicationContext(FactoryConfiguration.class);
    context.registerShutdownHook();
  }

  @Test
  void testFactoryBean() {
    PaymentGatewayClient paymentGatewayClient = context.getBean(PaymentGatewayClient.class);

    Assertions.assertNotNull(paymentGatewayClient);
    Assertions.assertEquals("https://payment-gateway.local", paymentGatewayClient.getEndpoint());
    Assertions.assertEquals("PRIVATE_KEY", paymentGatewayClient.getPrivateKey());
    Assertions.assertEquals("PUBLIC_KEY", paymentGatewayClient.getPublicKey());
  }
}
