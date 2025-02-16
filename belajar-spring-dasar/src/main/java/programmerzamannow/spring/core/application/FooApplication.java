package programmerzamannow.spring.core.application;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import programmerzamannow.spring.core.data.Bar;
import programmerzamannow.spring.core.data.Foo;
import programmerzamannow.spring.core.listener.AppStartingListener;

import java.util.List;

@SpringBootApplication
public class FooApplication {

//  For this case, is for testing Failure Analyzer in Spring Boot
//  @Bean
//  public Foo foo(Bar bar) {
//    return new Foo();
//  }

  @Bean
  public Foo foo() {
    return new Foo();
  }

//  This for run
//  public static void main(String[] args) {
//    ConfigurableApplicationContext applicationContex = SpringApplication.run(FooApplication.class, args);
//
//    Foo foo = applicationContex.getBean(Foo.class);
//    System.out.println(foo);
//  }

  // this for manual custom SpringApplication
  public static void main(String[] args) {
    SpringApplication application = new SpringApplication(FooApplication.class);
    application.setBannerMode(Banner.Mode.OFF);
    application.setListeners(List.of(new AppStartingListener()));

    ConfigurableApplicationContext applicationContext = application.run(args);

    Foo foo = applicationContext.getBean(Foo.class);
    System.out.println(foo);
  }
}
