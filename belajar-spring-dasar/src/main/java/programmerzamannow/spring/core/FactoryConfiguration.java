package programmerzamannow.spring.core;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import programmerzamannow.spring.core.client.PaymentGatewayClient;
import programmerzamannow.spring.core.factory.PaymentGatewayClientFactoryBean;

@Configuration
@Import( {
    PaymentGatewayClientFactoryBean.class,
})
public class FactoryConfiguration {
}
