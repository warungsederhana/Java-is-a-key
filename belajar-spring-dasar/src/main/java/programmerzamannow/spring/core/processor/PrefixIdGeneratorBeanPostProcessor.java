package programmerzamannow.spring.core.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import programmerzamannow.spring.core.aware.IdAware;

import java.util.UUID;

@Component
@Slf4j
public class PrefixIdGeneratorBeanPostProcessor implements BeanPostProcessor, Ordered {
  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    log.info("Prefix Id Generator Processor for Bean: {} {}", bean, beanName);
    if (bean instanceof IdAware idAware) {
      log.info("Generate Prefix ID for {}", beanName);
      idAware.setId("PZN-" + idAware.getId());
    }

    return bean;
  }

  @Override
  public int getOrder() {
    return 2;
  }
}
