package programmerzamannow.spring.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import programmerzamannow.spring.core.data.Bar;
import programmerzamannow.spring.core.data.Foo;

@Slf4j
@Configuration
public class DependsOnConfiguration {

  @Bean
  @Lazy       // create Foo when it's needed
  @DependsOn(value = {"bar"})
  public Foo foo() {
    log.info("Create Foo");
    return new Foo();
  }

  @Bean
  public Bar bar() {
    log.info("Create Bar");
    return new Bar();
  }
}
