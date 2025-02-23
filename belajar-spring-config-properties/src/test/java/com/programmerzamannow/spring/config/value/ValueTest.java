package com.programmerzamannow.spring.config.value;

import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

@SpringBootTest(classes = ValueTest.TestApplication.class)
public class ValueTest {

  @Autowired
  private TestApplication.ApplicationProperties applicationProperties;

  @Autowired
  private TestApplication.SystemProperties systemProperties;

  @Test
  void testValueApplicationProperties() {
    Assertions.assertEquals("Belajar Spring Boot", applicationProperties.getName());
    Assertions.assertEquals(1, applicationProperties.getVersion());
    Assertions.assertFalse(applicationProperties.getIsProduction());
  }

  @Test
  @Disabled
  void testValueSystemProperties() {
    Assertions.assertNotNull(systemProperties.getJavaHome());
    Assertions.assertEquals("C:\\Program Files\\Zulu\\zulu-17", systemProperties.getJavaHome());
  }

  @SpringBootApplication
  public static class TestApplication {

    @Component
    @Getter
    public static class ApplicationProperties {

      @Value("${application.name}")
      private String name;

      @Value("${application.version}")
      private Integer version;

      @Value("${application.production-mode}")
      private Boolean isProduction;

    }

    @Component
    @Getter
    public static class SystemProperties {

      @Value("${JAVA_HOME}")
      private String javaHome;

    }
  }
}
