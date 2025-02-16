package programmerzamannow.spring.core.commandapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CommandApp {

  public static void main(String[] args) {
    ConfigurableApplicationContext application = SpringApplication.run(CommandApp.class, args);
  }
}
