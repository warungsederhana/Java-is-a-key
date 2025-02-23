package com.programmerzamannow.spring.config.profileenvironment;

import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest(classes = ProfileEnvironmentTest.TestApplication.class)
@ActiveProfiles({"development", "production"})
public class ProfileEnvironmentTest {

  @Autowired
  TestApplication.SampleProfile sampleProfile;

  @Test
  void testProfile() {
    String[] profiles = sampleProfile.getProfiles();

    Assertions.assertArrayEquals(new String[] {"development", "production"}, profiles);
    for (String profile : profiles) {
      System.out.println(profile);
    }
  }

  @SpringBootApplication
  public static class TestApplication {

    @Component
    public static class SampleProfile implements EnvironmentAware {

      @Setter
      private Environment environment;

      public String[] getProfiles() {
        return environment.getActiveProfiles();
      }
    }
  }
}
