package programmerzamannow.restful.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.sql.Update;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;
import programmerzamannow.restful.entity.Contact;
import programmerzamannow.restful.entity.User;
import programmerzamannow.restful.model.WebResponse;
import programmerzamannow.restful.model.contact.ContactResponse;
import programmerzamannow.restful.model.contact.CreateContactRequest;
import programmerzamannow.restful.model.contact.UpdateContactRequest;
import programmerzamannow.restful.repository.ContactRepository;
import programmerzamannow.restful.repository.UserRepository;
import programmerzamannow.restful.security.BCrypt;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class ContactControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ContactRepository contactRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    contactRepository.deleteAll();
    userRepository.deleteAll();

    User user = new User();
    user.setUsername("test");
    user.setName("test");
    user.setPassword(BCrypt.hashpw("password", BCrypt.gensalt()));
    user.setToken("test");
    user.setTokenExpiredAt(System.currentTimeMillis() + (30L * 24 * 60 * 60 * 1000));
    userRepository.save(user);
  }

  @Test
  void createContactBadRequest() throws Exception {
    CreateContactRequest request = new CreateContactRequest();
    request.setFirstName("");
    request.setEmail("salah");

    mockMvc.perform(
        post("/api/contacts")
            .header("X-API-TOKEN", "test")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(request))
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
  void createContactSuccess() throws Exception {
    CreateContactRequest request = new CreateContactRequest();
    request.setFirstName("Eko");
    request.setLastName("Kurniawan");
    request.setEmail("eko@example.com");
    request.setPhone("081234567890");

    mockMvc.perform(
        post("/api/contacts")
            .header("X-API-TOKEN", "test")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(request))
    ).andExpectAll(
        status().isOk()
    ).andDo(result -> {
      String contentAsString = result.getResponse().getContentAsString();
      WebResponse<ContactResponse> response = objectMapper.readValue(contentAsString, new TypeReference<>() {
      });

      assertNull(response.getErrors());
      assertNotNull(response.getData());
      assertEquals("Eko", response.getData().getFirstName());
      assertEquals("Kurniawan", response.getData().getLastName());
      assertEquals("eko@example.com", response.getData().getEmail());
      assertEquals("081234567890", response.getData().getPhone());
      assertNotNull(response.getData().getId());

      assertTrue(contactRepository.existsById(response.getData().getId()));
    });
  }

  @Test
  void getContactNotFound() throws Exception {
    mockMvc.perform(
        get("/api/contacts/not-found")
            .header("X-API-TOKEN", "test")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
    ).andExpectAll(
        status().isNotFound()
    ).andDo(result -> {
      String contentAsString = result.getResponse().getContentAsString();
      WebResponse<String> response = objectMapper.readValue(contentAsString, new TypeReference<>() {
      });

      assertNotNull(response.getErrors());
    });
  }

  @Test
  void getContactSuccess() throws Exception {
    User user = userRepository.findById("test")
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found"));

    Contact contact = new Contact();
    contact.setId(UUID.randomUUID().toString());
    contact.setUser(user);
    contact.setFirstName("Eko");
    contact.setLastName("Kurniawan");
    contact.setEmail("eko@example.com");
    contact.setPhone("081234567890");
    contactRepository.save(contact);

    mockMvc.perform(
        get("/api/contacts/" + contact.getId())
            .header("X-API-TOKEN", "test")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
    ).andExpectAll(
        status().isOk()
    ).andDo(result -> {
      String contentAsString = result.getResponse().getContentAsString();
      WebResponse<ContactResponse> response = objectMapper.readValue(contentAsString, new TypeReference<>() {
      });

      assertNull(response.getErrors());
      assertNotNull(response.getData());

      assertEquals(contact.getFirstName(), response.getData().getFirstName());
      assertEquals(contact.getLastName(), response.getData().getLastName());
      assertEquals(contact.getEmail(), response.getData().getEmail());
      assertEquals(contact.getPhone(), response.getData().getPhone());
      assertEquals(contact.getId(), response.getData().getId());
    });
  }

  @Test
  void updateContactBadRequest() throws Exception {
    UpdateContactRequest request = new UpdateContactRequest();
    request.setFirstName("");
    request.setEmail("salah");

    mockMvc.perform(
        put("/api/contacts/not-found")
            .header("X-API-TOKEN", "test")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(request))
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
  void updateContactSuccess() throws Exception {
    User user = userRepository.findById("test")
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found"));

    Contact contact = new Contact();
    contact.setId(UUID.randomUUID().toString());
    contact.setUser(user);
    contact.setFirstName("Eko");
    contact.setLastName("Kurniawan");
    contact.setEmail("eko@example.com");
    contact.setPhone("081234567890");
    contactRepository.save(contact);

    UpdateContactRequest request = new UpdateContactRequest();
    request.setFirstName("firstName Updated");
    request.setLastName("lastName Updated");
    request.setEmail("updated@example.com");
    request.setPhone("081209876543");

    mockMvc.perform(
        put("/api/contacts/"+ contact.getId())
            .header("X-API-TOKEN", "test")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(request))
    ).andExpectAll(
        status().isOk()
    ).andDo(result -> {
      String contentAsString = result.getResponse().getContentAsString();
      WebResponse<ContactResponse> response = objectMapper.readValue(contentAsString, new TypeReference<>() {
      });

      assertNull(response.getErrors());
      assertNotNull(response.getData());
      assertEquals(request.getFirstName(), response.getData().getFirstName());
      assertEquals(request.getLastName(), response.getData().getLastName());
      assertEquals(request.getEmail(), response.getData().getEmail());
      assertEquals(request.getPhone(), response.getData().getPhone());
      assertNotNull(response.getData().getId());

      assertTrue(contactRepository.existsById(response.getData().getId()));
    });
  }

  @Test
  void deleteContactNotFound() throws Exception {
    mockMvc.perform(
        delete("/api/contacts/not-found")
            .header("X-API-TOKEN", "test")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
    ).andExpectAll(
        status().isNotFound()
    ).andDo(result -> {
      String contentAsString = result.getResponse().getContentAsString();
      WebResponse<String> response = objectMapper.readValue(contentAsString, new TypeReference<>() {
      });

      assertNotNull(response.getErrors());
    });
  }

  @Test
  void deleteContactSuccess() throws Exception {
    User user = userRepository.findById("test")
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found"));

    Contact contact = new Contact();
    contact.setId(UUID.randomUUID().toString());
    contact.setUser(user);
    contact.setFirstName("Eko");
    contact.setLastName("Kurniawan");
    contact.setEmail("eko@example.com");
    contact.setPhone("081234567890");
    contactRepository.save(contact);

    mockMvc.perform(
        delete("/api/contacts/" + contact.getId())
            .header("X-API-TOKEN", "test")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
    ).andExpectAll(
        status().isOk()
    ).andDo(result -> {
      String contentAsString = result.getResponse().getContentAsString();
      WebResponse<String> response = objectMapper.readValue(contentAsString, new TypeReference<>() {
      });

      assertNull(response.getErrors());
      assertNotNull(response.getData());
      assertEquals("OK", response.getData());

      Contact contactDb = contactRepository.findFirstByUserAndId(user, contact.getId()).orElse(null);
      assertNull(contactDb);
    });
  }

  @Test
  void searchNotFound() throws Exception {
    mockMvc.perform(
        get("/api/contacts")
            .header("X-API-TOKEN", "test")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
    ).andExpectAll(
        status().isOk()
    ).andDo(result -> {
      String contentAsString = result.getResponse().getContentAsString();
      WebResponse<List<ContactResponse>> response = objectMapper.readValue(contentAsString, new TypeReference<>() {
      });

      assertNull(response.getErrors());
      assertNotNull(response.getData());
      assertEquals(0, response.getData().size());
      assertEquals(0, response.getPaging().getTotalPage());
      assertEquals(0, response.getPaging().getCurrentPage());
      assertEquals(10, response.getPaging().getSize());
    });
  }

  @Test
  void searchSuccess() throws Exception {
    User user = userRepository.findById("test")
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found"));

    for (int i = 0; i < 100; i++) {
      Contact contact = new Contact();
      contact.setId(UUID.randomUUID().toString());
      contact.setUser(user);
      contact.setFirstName("Eko " + i);
      contact.setLastName("Kurniawan " + i);
      contact.setEmail("eko" + i + "@example.com");
      contact.setPhone("081234567890");
      contactRepository.save(contact);
    }

    // search with name (first name)
    mockMvc.perform(
        get("/api/contacts")
            .header("X-API-TOKEN", "test")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .queryParam("name", "Eko")
    ).andExpectAll(
        status().isOk()
    ).andDo(result -> {
      String contentAsString = result.getResponse().getContentAsString();
      WebResponse<List<ContactResponse>> response = objectMapper.readValue(contentAsString, new TypeReference<>() {
      });

      assertNull(response.getErrors());
      assertNotNull(response.getData());
      assertEquals(10, response.getData().size());
      assertEquals(10, response.getPaging().getTotalPage());
      assertEquals(0, response.getPaging().getCurrentPage());
      assertEquals(10, response.getPaging().getSize());
    });

    // search with name (last name)
    mockMvc.perform(
        get("/api/contacts")
            .header("X-API-TOKEN", "test")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .queryParam("name", "Kurniawan")
    ).andExpectAll(
        status().isOk()
    ).andDo(result -> {
      String contentAsString = result.getResponse().getContentAsString();
      WebResponse<List<ContactResponse>> response = objectMapper.readValue(contentAsString, new TypeReference<>() {
      });

      assertNull(response.getErrors());
      assertNotNull(response.getData());
      assertEquals(10, response.getData().size());
      assertEquals(10, response.getPaging().getTotalPage());
      assertEquals(0, response.getPaging().getCurrentPage());
      assertEquals(10, response.getPaging().getSize());
    });

    // search with email
    mockMvc.perform(
        get("/api/contacts")
            .header("X-API-TOKEN", "test")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .queryParam("email", "example.com")
    ).andExpectAll(
        status().isOk()
    ).andDo(result -> {
      String contentAsString = result.getResponse().getContentAsString();
      WebResponse<List<ContactResponse>> response = objectMapper.readValue(contentAsString, new TypeReference<>() {
      });

      assertNull(response.getErrors());
      assertNotNull(response.getData());
      assertEquals(10, response.getData().size());
      assertEquals(10, response.getPaging().getTotalPage());
      assertEquals(0, response.getPaging().getCurrentPage());
      assertEquals(10, response.getPaging().getSize());
    });

    // search with phone
    mockMvc.perform(
        get("/api/contacts")
            .header("X-API-TOKEN", "test")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .queryParam("phone", "7890")
    ).andExpectAll(
        status().isOk()
    ).andDo(result -> {
      String contentAsString = result.getResponse().getContentAsString();
      WebResponse<List<ContactResponse>> response = objectMapper.readValue(contentAsString, new TypeReference<>() {
      });

      assertNull(response.getErrors());
      assertNotNull(response.getData());
      assertEquals(10, response.getData().size());
      assertEquals(10, response.getPaging().getTotalPage());
      assertEquals(0, response.getPaging().getCurrentPage());
      assertEquals(10, response.getPaging().getSize());
    });

    // search with page
    mockMvc.perform(
        get("/api/contacts")
            .header("X-API-TOKEN", "test")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .queryParam("phone", "7890")
            .queryParam("page", "1000")
    ).andExpectAll(
        status().isOk()
    ).andDo(result -> {
      String contentAsString = result.getResponse().getContentAsString();
      WebResponse<List<ContactResponse>> response = objectMapper.readValue(contentAsString, new TypeReference<>() {
      });

      assertNull(response.getErrors());
      assertNotNull(response.getData());
      assertEquals(0, response.getData().size());
      assertEquals(10, response.getPaging().getTotalPage());
      assertEquals(1000, response.getPaging().getCurrentPage());
      assertEquals(10, response.getPaging().getSize());
    });
  }
}