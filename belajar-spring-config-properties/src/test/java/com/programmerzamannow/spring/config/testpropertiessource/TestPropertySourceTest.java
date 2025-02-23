package com.programmerzamannow.spring.config.testpropertiessource;

import com.programmerzamannow.spring.config.propertysource.PropertySourceTest;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.TestPropertySources;

@SpringBootTest(classes = TestPropertySourceTest.TestApplication.class)
@TestPropertySources({
    @TestPropertySource("classpath:test.properties"),
})
public class TestPropertySourceTest {

  @Autowired
  private TestApplication.SampleProperties sampleProperties;

  @Test
  void testPropertySource() {
    Assertions.assertEquals("Sample Project Test", sampleProperties.getName());
    Assertions.assertEquals(1, sampleProperties.getVersion());

    System.out.println(sampleProperties.getName());
    System.out.println(sampleProperties.getVersion());
  }

  @SpringBootApplication
  public static class TestApplication {

    @Component
    @Getter
    public static class SampleProperties {

      @Value("${sample.name}")
      private String name;

      @Value("${sample.version}")
      private Integer version;
    }
  }
}
