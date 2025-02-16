package com.programmerzamannow.spring.config.resourceloader;

import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@SpringBootTest(classes=ResourceLoaderTest.TestApplication.class)
public class ResourceLoaderTest {

  @Autowired
  private TestApplication.SampleResource sampleResource;

  @Test
  void testResourceLoader() {
    String text = sampleResource.getText();
    Assertions.assertEquals("TESTING RESOURCE LOADER", text);
    System.out.println(text);
  }

  @SpringBootApplication
  public static class TestApplication {

    @Component
    public static class SampleResource implements ResourceLoaderAware {

      @Setter
      private ResourceLoader resourceLoader;

      public String getText() {
        Resource resource = resourceLoader.getResource("classpath:/text/resource.txt");
        try (InputStream inputStream = resource.getInputStream();) {
          return new String(inputStream.readAllBytes());
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }
}
