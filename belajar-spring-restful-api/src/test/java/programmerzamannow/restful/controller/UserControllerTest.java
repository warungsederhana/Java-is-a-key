package programmerzamannow.restful.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import programmerzamannow.restful.entity.User;
import programmerzamannow.restful.model.user.RegisterUserRequest;
import programmerzamannow.restful.model.WebResponse;
import programmerzamannow.restful.model.user.UpdateUserRequest;
import programmerzamannow.restful.model.user.UserResponse;
import programmerzamannow.restful.repository.UserRepository;
import programmerzamannow.restful.security.BCrypt;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private UserRepository userRepository;

  @BeforeEach
  void setUp() {
    userRepository.deleteAll();
  }

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void testRegisterSuccess() throws Exception {
    RegisterUserRequest request = new RegisterUserRequest();
    request.setUsername("johndoe");
    request.setPassword("password");
    request.setName("John Doe");
    String jsonRequest = objectMapper.writeValueAsString(request);

    mockMvc.perform(
        post("/api/users")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(jsonRequest)
    ).andExpectAll(
        status().isOk()
    ).andDo(result -> {
      String contentAsString = result.getResponse().getContentAsString();
      WebResponse<String> response = objectMapper.readValue(contentAsString, new TypeReference<>() {
      });

      assertEquals("OK", response.getData());
    });
  }

  @Test
  void testRegisterBadRequest() throws Exception {
    RegisterUserRequest request = new RegisterUserRequest();
    request.setUsername("");
    request.setPassword("");
    request.setName("");
    String jsonRequest = objectMapper.writeValueAsString(request);

    mockMvc.perform(
        post("/api/users")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(jsonRequest)
    ).andExpectAll(
        status().isBadRequest()
    ).andDo(result -> {
      String contentAsString = result.getResponse().getContentAsString();
      WebResponse<String> response = objectMapper.readValue(contentAsString, new TypeReference<>() {
      });

      assertNotNull(response.getErrors());
    });
  }

  @Test
  void testRegisterDuplicate() throws Exception {
    User user = new User();
    user.setUsername("test");
    user.setPassword(BCrypt.hashpw("test", BCrypt.gensalt()));
    user.setName("test");
    userRepository.save(user);

    RegisterUserRequest request = new RegisterUserRequest();
    request.setUsername("test");
    request.setPassword("test");
    request.setName("test");
    String jsonRequest = objectMapper.writeValueAsString(request);

    mockMvc.perform(
        post("/api/users")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(jsonRequest)
    ).andExpectAll(
        status().isBadRequest()
    ).andDo(result -> {
      String contentAsString = result.getResponse().getContentAsString();
      WebResponse<String> response = objectMapper.readValue(contentAsString, new TypeReference<>() {
      });

      assertNotNull(response.getErrors());
    });
  }

  @Test
  void getUserUnauthorizedInvalidToken() throws  Exception {
    mockMvc.perform(
        get("/api/users/current")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .header("X-API-TOKEN", "not-found")
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
  void getUserUnauthorizedTokenNotSend() throws  Exception {
    mockMvc.perform(
        get("/api/users/current")
            .accept(MediaType.APPLICATION_JSON_VALUE)
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
  void getUserUnauthorizedTokenExpired() throws  Exception {
    User user = new User();
    user.setUsername("test");
    user.setName("test");
    user.setPassword(BCrypt.hashpw("password", BCrypt.gensalt()));
    user.setToken("test");
    user.setTokenExpiredAt(System.currentTimeMillis() - (30L * 24 * 60 * 60 * 1000));
    userRepository.save(user);


    mockMvc.perform(
        get("/api/users/current")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .header("X-API-TOKEN", "test")
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
  void getUserSuccess() throws  Exception {
    User user = new User();
    user.setUsername("test");
    user.setName("test");
    user.setPassword(BCrypt.hashpw("password", BCrypt.gensalt()));
    user.setToken("test");
    user.setTokenExpiredAt(System.currentTimeMillis() + (30L * 24 * 60 * 60 * 1000));
    userRepository.save(user);


    mockMvc.perform(
        get("/api/users/current")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .header("X-API-TOKEN", "test")
    ).andExpectAll(
        status().isOk()
    ).andDo(result -> {
      String contentAsString = result.getResponse().getContentAsString();
      WebResponse<UserResponse> response = objectMapper.readValue(contentAsString, new TypeReference<>() {
      });

      assertNull(response.getErrors());
      assertEquals("test", response.getData().getUsername());
      assertEquals("test", response.getData().getName());
    });
  }

  @Test
  void updateUserUnauthorizedTokenNotSend() throws  Exception {
    UpdateUserRequest request = new UpdateUserRequest();
    request.setName("test");
    request.setPassword("test");
    String jsonRequest = objectMapper.writeValueAsString(request);

    mockMvc.perform(
        patch("/api/users/current")
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
  void updateUserSuccess() throws  Exception {
    User user = new User();
    user.setUsername("test");
    user.setName("test");
    user.setPassword(BCrypt.hashpw("password", BCrypt.gensalt()));
    user.setToken("test");
    user.setTokenExpiredAt(System.currentTimeMillis() + (30L * 24 * 60 * 60 * 1000));
    userRepository.save(user);

    UpdateUserRequest request = new UpdateUserRequest();
    request.setName("updated");
    request.setPassword("password-updated");
    String jsonRequest = objectMapper.writeValueAsString(request);

    mockMvc.perform(
        patch("/api/users/current")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .header("X-API-TOKEN", "test")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(jsonRequest)
    ).andExpectAll(
        status().isOk()
    ).andDo(result -> {
      String contentAsString = result.getResponse().getContentAsString();
      WebResponse<UserResponse> response = objectMapper.readValue(contentAsString, new TypeReference<>() {
      });

      assertNull(response.getErrors());
      assertEquals("updated", response.getData().getName());

      User userDb = userRepository.findById("test").orElse(null);
      assertNotNull(userDb);
      assertTrue(BCrypt.checkpw("password-updated", userDb.getPassword()));
    });
  }
}