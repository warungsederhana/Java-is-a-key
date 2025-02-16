package com.programmerzamannow.spring.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public class ResourceTest {

  @Test
  void testResource() throws IOException {
    System.out.println("Test Resource");
    var resource  = new ClassPathResource("/text/sampletext.txt");

    Assertions.assertNotNull(resource);
    Assertions.assertTrue(resource.exists());
    Assertions.assertNotNull(resource.getFile());
  }
}
