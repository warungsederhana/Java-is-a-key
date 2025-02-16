package programmerzamannow.spring.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import programmerzamannow.spring.core.data.Connection;

@Configuration
public class LifeCycleConfiguration {

  @Bean
  public Connection connection() {
    return new Connection();
  }
}
