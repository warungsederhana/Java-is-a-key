package programmerzamannow.restful.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import programmerzamannow.restful.entity.User;
import programmerzamannow.restful.model.WebResponse;
import programmerzamannow.restful.model.user.LoginUserRequest;
import programmerzamannow.restful.model.user.TokenResponse;
import programmerzamannow.restful.repository.UserRepository;
import programmerzamannow.restful.security.BCrypt;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    userRepository.deleteAll();
  }

  @Test
  void loginFailedUserNotFound() throws Exception {
    LoginUserRequest request = new LoginUserRequest();
    request.setUsername("test");
    request.setPassword("test");
    String jsonRequest = objectMapper.writeValueAsString(request);

    mockMvc.perform(
        post("/api/auth/login")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(jsonRequest)
    ).andExpectAll(
        status().isUnauthorized()
    ).andDo(result -> {
      String contentAsString = result.getResponse().getContentAsString();
      WebResponse<String> response = objectMapper.readValue(contentAsString, new TypeReference<>() {
      });

      assertNotNull(response.getErrors());
    });
  }

  @Test
  void loginFailedWrongPassword() throws Exception {
    User user = new User();
    user.setUsername("test");
    user.setName("test");
    user.setPassword(BCrypt.hashpw("test", BCrypt.gensalt()));
    userRepository.save(user);

    LoginUserRequest request = new LoginUserRequest();
    request.setUsername("test");
    request.setPassword("salah");
    String jsonRequest = objectMapper.writeValueAsString(request);

    mockMvc.perform(
        post("/api/auth/login")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(jsonRequest)
    ).andExpectAll(
        status().isUnauthorized()
    ).andDo(result -> {
      String contentAsString = result.getResponse().getContentAsString();
      WebResponse<String> response = objectMapper.readValue(contentAsString, new TypeReference<>() {
      });

      assertNotNull(response.getErrors());
    });
  }

  @Test
  void loginSuccess() throws Exception {
    User user = new User();
    user.setUsername("test");
    user.setName("test");
    user.setPassword(BCrypt.hashpw("test", BCrypt.gensalt()));
    userRepository.save(user);

    LoginUserRequest request = new LoginUserRequest();
    request.setUsername("test");
    request.setPassword("test");
    String jsonRequest = objectMapper.writeValueAsString(request);

    mockMvc.perform(
        post("/api/auth/login")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(jsonRequest)
    ).andExpectAll(
        status().isOk()
    ).andDo(result -> {
      String contentAsString = result.getResponse().getContentAsString();
      WebResponse<TokenResponse> response = objectMapper.readValue(contentAsString, new TypeReference<>() {
      });

      assertNull(response.getErrors());
      assertNotNull(response.getData().getToken());
      assertNotNull(response.getData().getExpiredAt());

      User userDb = userRepository.findById("test").orElse(null);
      assertNotNull(userDb);
      assertEquals(userDb.getToken(), response.getData().getToken());
      assertEquals(userDb.getTokenExpiredAt(), response.getData().getExpiredAt());
    });
  }
}