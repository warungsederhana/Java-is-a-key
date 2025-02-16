package programmerzamannow.spring.core;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import programmerzamannow.spring.core.data.Bar;
import programmerzamannow.spring.core.data.Foo;
import programmerzamannow.spring.core.data.FooBar;

@Configuration
public class DependencyInjectionConfiguration {

  @Bean(value = "fooFirst")
  @Primary
  public Foo foo() {
    return new Foo();
  }

  @Bean(value = "fooSecond")
  public Foo foo2() {
    return new Foo();
  }

  @Bean
  public Bar bar() {
    return new Bar();
  }

  @Bean
  // if there is no qualifier, spring will use the primary bean
  public FooBar fooBar(@Qualifier("fooSecond") Foo foo, Bar bar) {
    return new FooBar(foo, bar);
  }
}
