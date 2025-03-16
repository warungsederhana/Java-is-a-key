package programmerzamannow.springmvc.controllers;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.print.attribute.standard.Media;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class FormControllerTest {

  @Autowired
  private MockMvc mockMvc;

//  @Test
//  void formHello() throws Exception {
//    mockMvc.perform(
//        post("/form/hello")
//            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//            .param("name", "eko")
//    ).andExpectAll(
//        status().isOk(),
//        content().string(Matchers.containsString("Hello eko"))
//    );
//  }

  // update with response content type
  @Test
  void formHello() throws Exception {
    mockMvc.perform(
        post("/form/hello")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("name", "eko")
    ).andExpectAll(
        status().isOk(),
        header().string(HttpHeaders.CONTENT_TYPE, Matchers.containsString(MediaType.TEXT_HTML_VALUE)),
        content().string(Matchers.containsString("Hello eko"))
    );
  }

  @Test
  void createPerson() throws Exception {
    mockMvc.perform(
        post("/ form/person")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .param("name", "eko")
            .param("birthDate", "2021-08-01")
            .param("address", "Indonesia")
    ).andExpectAll(
        status().isOk(),
        content().string(Matchers.containsString("Success create person with name :eko and address :Indonesia and birth date :2021-08-01"))
    );
  }
}