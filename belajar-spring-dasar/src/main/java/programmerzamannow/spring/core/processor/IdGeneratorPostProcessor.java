package programmerzamannow.spring.core.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import programmerzamannow.spring.core.aware.IdAware;

import java.util.UUID;

@Slf4j
@Component
public class IdGeneratorPostProcessor implements BeanPostProcessor, Ordered {

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    log.info("postProcessBeforeInitialization {} {}", bean, beanName);
    if (bean instanceof IdAware idAware) {
      log.info("Generate ID for {}", beanName);
      idAware.setId(UUID.randomUUID().toString());
    }

    return bean;
  }

  @Override
  public int getOrder() {
    return 1;
  }
}
