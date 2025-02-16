package com.programmerzamannow.spring.config.messagesource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

public class MessageSourceTest {

  private ConfigurableApplicationContext context;

  private MessageSource messageSource;

  @BeforeEach
  void setUp() {
    context = new AnnotationConfigApplicationContext(TestApplication.class);
    messageSource = context.getBean(MessageSource.class);
    context.registerShutdownHook();
  }

  @Test
  void testDefaultLocale() {
    String message = messageSource.getMessage("hello", new Object[]{"Eko"}, Locale.ENGLISH);
    Assertions.assertEquals("Hello Eko", message);
  }

  @Test
  void testIndonesianLocale() {
    String message = messageSource.getMessage("hello", new Object[]{"Eko"}, new Locale("in_ID"));
    Assertions.assertEquals("Halo Eko", message);
  }

  @SpringBootApplication
  public static class TestApplication {

    @Bean
    public MessageSource messageSource() {
      ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
      messageSource.setBasenames("my");
      return messageSource;
    }
  }
}
