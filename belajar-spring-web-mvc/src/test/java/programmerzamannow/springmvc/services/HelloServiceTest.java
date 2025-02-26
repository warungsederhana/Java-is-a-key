package programmerzamannow.springmvc.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HelloServiceTest {

  @Autowired
  private IHelloService helloService;

  @Test
  void testHello() {
    String result = helloService.hello("Eko");
    assertEquals("Hello Eko", result);
  }
}