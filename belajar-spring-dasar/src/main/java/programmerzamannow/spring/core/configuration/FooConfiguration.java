package programmerzamannow.spring.core.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import programmerzamannow.spring.core.data.Foo;

@Configuration
public class FooConfiguration {

  @Bean
  public Foo foo() {
    return new Foo();
  }
}
