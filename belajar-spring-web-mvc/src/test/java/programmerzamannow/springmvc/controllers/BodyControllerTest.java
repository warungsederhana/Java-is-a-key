package programmerzamannow.springmvc.controllers;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.ResultActions;
import programmerzamannow.springmvc.models.HelloRequest;
import programmerzamannow.springmvc.models.HelloResponse;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BodyControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void bodyHello() throws Exception {
    HelloRequest request = new HelloRequest();
    request.setName("Eko");

    String requestJson = objectMapper.writeValueAsString(request);

    mockMvc.perform(
        post("/body/hello")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(requestJson)
    ).andExpectAll(
        status().isOk()
    ).andExpect(result -> {
      String responseJson = result.getResponse().getContentAsString();
      HelloResponse helloResponse = objectMapper.readValue(responseJson, HelloResponse.class);
      assertEquals("Hello Eko", helloResponse.getHello());
    });
  }
}