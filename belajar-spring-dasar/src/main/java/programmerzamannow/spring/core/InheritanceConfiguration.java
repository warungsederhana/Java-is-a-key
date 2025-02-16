package programmerzamannow.spring.core;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import programmerzamannow.spring.core.service.MerchantService;

@Configuration
@Import({
    MerchantService.class
})
public class InheritanceConfiguration {
  
}
