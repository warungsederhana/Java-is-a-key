package com.programmerzamannow.spring.config.environment;

import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest(classes = EnvironmentTest.TestApplication.class)
public class EnvironmentTest {

  @Autowired
  private Environment environment;

  @Test
  @Disabled
  void testEnvironment() {
    String property = environment.getProperty("JAVA_HOME");
    Assertions.assertEquals("C:\\Program Files\\Zulu\\zulu-17", property);
  }

  @SpringBootApplication
  public static class TestApplication {
  }
}
