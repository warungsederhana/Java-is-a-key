package programmerzamannow.springmvc.controllers;

import jakarta.servlet.http.Cookie;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void loginSucess() throws Exception {
    mockMvc.perform(
        post("/auth/login")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("username", "eko")
            .param("password", "rahasia")
    ).andExpectAll(
        status().isOk(),
        content().string(Matchers.containsString("OK")),
        cookie().value("username", Matchers.containsString("eko"))
    );
  }

  @Test
  void loginFailed() throws Exception {
    mockMvc.perform(
        post("/auth/login")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("username", "eko")
            .param("password", "Test")
    ).andExpectAll(
        status().isUnauthorized(),
        content().string(Matchers.containsString("KO"))
    );
  }

  @Test
  void getUser() throws Exception {
    mockMvc.perform(
        get("/auth/user")
            .cookie(new Cookie("username", "eko"))
    ).andExpectAll(
        status().isOk(),
        content().string(Matchers.containsString("Hello eko"))
    );
  }
}