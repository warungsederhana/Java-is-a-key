package programmerzamannow.springmvc.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerIntegrationTest {

  @LocalServerPort
  private Integer port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  void testHelloGuest() {
    String response = restTemplate.getForEntity("http://localhost:" + port + "/hello", String.class).getBody();
    Assertions.assertNotNull(response);
    Assertions.assertEquals("Hello Guest", response.trim());
  }

  @Test
  void testHelloEko() {
    String response = restTemplate.getForEntity("http://localhost:" + port + "/hello?name=Eko", String.class).getBody();
    Assertions.assertNotNull(response);
    Assertions.assertEquals("Hello Eko", response.trim());
  }
}
