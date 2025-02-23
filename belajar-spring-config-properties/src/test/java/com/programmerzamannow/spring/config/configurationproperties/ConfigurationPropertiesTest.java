package com.programmerzamannow.spring.config.configurationproperties;


import com.programmerzamannow.spring.config.converters.StringToDateConverter;
import com.programmerzamannow.spring.config.properties.ApplicationProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.ConversionService;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;

@SpringBootTest
public class ConfigurationPropertiesTest {

  @Autowired
  private ApplicationProperties applicationProperties;

  @Autowired
  private ConversionService conversionService;

  @Test
  void testConversionService() {
    Assertions.assertTrue(conversionService.canConvert(String.class, Duration.class));
    Assertions.assertTrue(conversionService.canConvert(String.class, Date.class));

    Assertions.assertEquals(Duration.ofSeconds(10), conversionService.convert("10s", Duration.class));
  }

  @Test
  void testConfigurationProperties() {
    Assertions.assertEquals("Belajar Spring Boot", applicationProperties.getName());
    Assertions.assertEquals(1, applicationProperties.getVersion());
    Assertions.assertFalse(applicationProperties.getIsProduction());
  }

  @Test
  void testDatabaseProperties() {
    Assertions.assertEquals("belajar_spring_boot", applicationProperties.getDatabase().getDatabase());
    Assertions.assertEquals("admin", applicationProperties.getDatabase().getUsername());
    Assertions.assertEquals("admin123", applicationProperties.getDatabase().getPassword());
    Assertions.assertEquals("jdbc:mysql://localhost:3306/belajar_spring_boot", applicationProperties.getDatabase().getUrl());
  }

  @Test
  void testCollectionProperties() {
    Assertions.assertEquals(Arrays.asList("products", "categories", "customers"), applicationProperties.getDatabase().getWhitelistTables());
    Assertions.assertEquals(100, applicationProperties.getDatabase().getMaxTableSize().get("products"));
    Assertions.assertEquals(200, applicationProperties.getDatabase().getMaxTableSize().get("customers"));
    Assertions.assertEquals(300, applicationProperties.getDatabase().getMaxTableSize().get("categories"));
  }

  @Test
  void testEmbededCollectionProperties() {
    Assertions.assertEquals("default", applicationProperties.getDefaultRoles().get(0).getId());
    Assertions.assertEquals("Default role", applicationProperties.getDefaultRoles().get(0).getName());
    Assertions.assertEquals("guest", applicationProperties.getDefaultRoles().get(1).getId());
    Assertions.assertEquals("Guest role", applicationProperties.getDefaultRoles().get(1).getName());

    Assertions.assertEquals("admin", applicationProperties.getRoles().get("admin").getId());
    Assertions.assertEquals("Admin role", applicationProperties.getRoles().get("admin").getName());
    Assertions.assertEquals("finance", applicationProperties.getRoles().get("finance").getId());
    Assertions.assertEquals("Finance role", applicationProperties.getRoles().get("finance").getName());
  }

  @Test
  void testDurationProperties() {
    Assertions.assertEquals(Duration.ofSeconds(10), applicationProperties.getDefaultTimeout());
  }

  @Test
  void testCustomConverter() {
    Date expiredDate = applicationProperties.getExpiredDate();

    var dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Assertions.assertEquals("2010-10-10", dateFormat.format(expiredDate));
  }

  @SpringBootApplication
  @EnableConfigurationProperties({
      ApplicationProperties.class
  })
  @Import({
      StringToDateConverter.class
  })
  public static class TestApplication {

    @Bean
    public ConversionService conversionService(StringToDateConverter stringToDateConverter) {
      ApplicationConversionService conversionService = new ApplicationConversionService();
      conversionService.addConverter(stringToDateConverter);
      return conversionService;
    }
  }
}
