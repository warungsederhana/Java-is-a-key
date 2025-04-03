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
import programmerzamannow.restful.model.user.RegisterUserRequest;
import programmerzamannow.restful.model.WebResponse;
import programmerzamannow.restful.repository.UserRepository;
import programmerzamannow.restful.security.BCrypt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
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
}