package com.programmerzamannow.spring.config.appproperties;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest(classes = ApplicationPropertiesTest.TestApplication.class)
public class ApplicationPropertiesTest {

  @Autowired
  Environment environment;

  @Test
  void testApplicationProperties() {
    String property = environment.getProperty("application.name");
    Assertions.assertEquals("Belajar Spring Boot", property);
  }

  @SpringBootApplication
  public static class TestApplication {

  }
}
