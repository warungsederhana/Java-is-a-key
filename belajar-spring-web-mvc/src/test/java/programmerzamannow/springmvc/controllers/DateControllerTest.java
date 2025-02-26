package programmerzamannow.springmvc.controllers;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class DateControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void testGetDate() throws Exception {
    mockMvc.perform(
      get("/date")
          .queryParam("date", "2021-08-17")
    ).andExpectAll(
        status().isOk(),
        content().string(Matchers.containsString("Date : 20210817"))
    );
  }
}