package com.programmerzamannow.spring.config.profile;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest(classes = ProfileTest.TestApplication.class)
// we can use @ActiveProfiles to activate profile (e.g. @ActiveProfiles("local"))
// that annotation is doesn't care about the profile that we set in the application.properties
@ActiveProfiles({"local"})
public class ProfileTest {

  @Autowired
  private TestApplication.ISayHello sayHello;

  @Test
  void testProfile() {
    String result = sayHello.say("Eko");
    Assertions.assertEquals("Hello Eko from local", result);
  }

  @SpringBootApplication
  public static class TestApplication {

    public interface ISayHello {
      String say(String name);
    }

    @Component
    @Profile({"local"})
    public static class SayHelloLocal implements ISayHello {
      @Override
      public String say(String name) {
        return "Hello " + name + " from local";
      }
    }

    @Component
    @Profile({"production"})
    public static class SayHelloProduction implements ISayHello {
      @Override
      public String say(String name) {
        return "Hello " + name + " from production";
      }
    }
  }
}
