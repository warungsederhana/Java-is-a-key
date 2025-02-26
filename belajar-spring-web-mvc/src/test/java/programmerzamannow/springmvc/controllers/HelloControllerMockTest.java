package programmerzamannow.springmvc.controllers;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import programmerzamannow.springmvc.services.HelloService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerMockTest {

  @Autowired
  private MockMvc mockMvc;

  // Comment this line below when run with MockMvc
  @MockitoBean
  private HelloService helloService;

  @BeforeEach
  void setUp() {
    Mockito.when(helloService.hello(Mockito.anyString())).thenReturn("Hello Guys");
  }

  @Test
  // add disabled annotation when run with MockMvc
  void helloNameMock() throws Exception {
    mockMvc.perform(
        get("/hello")
            .queryParam("name", "Eko")
    ).andExpectAll(
        status().isOk(),
        content().string(Matchers.containsString("Hello Guys"))
    );
  }
}