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
import programmerzamannow.restful.entity.Address;
import programmerzamannow.restful.entity.Contact;
import programmerzamannow.restful.entity.User;
import programmerzamannow.restful.model.WebResponse;
import programmerzamannow.restful.model.address.AddressResponse;
import programmerzamannow.restful.model.address.CreateAddressRequest;
import programmerzamannow.restful.repository.AddressRepository;
import programmerzamannow.restful.repository.ContactRepository;
import programmerzamannow.restful.repository.UserRepository;
import programmerzamannow.restful.security.BCrypt;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.MockMvc.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AddressControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ContactRepository contactRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AddressRepository addressRepository;

  @Autowired
  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    addressRepository.deleteAll();
    contactRepository.deleteAll();
    userRepository.deleteAll();

    User user = new User();
    user.setUsername("test");
    user.setName("test");
    user.setPassword(BCrypt.hashpw("password", BCrypt.gensalt()));
    user.setToken("test");
    user.setTokenExpiredAt(System.currentTimeMillis() + (30L * 24 * 60 * 60 * 1000));
    userRepository.save(user);

    Contact contact = new Contact();
    contact.setId("test");
    contact.setUser(user);
    contact.setFirstName("Eko");
    contact.setLastName("Kurniawan");
    contact.setEmail("eko@example.com");
    contact.setPhone("081234567890");
    contactRepository.save(contact);
  }

  @Test
  void createAddressBadRequest() throws Exception {
    CreateAddressRequest addressRequest= new CreateAddressRequest();
    addressRequest.setCountry("");

    mockMvc.perform(
        post("/api/contacts/test/addresses")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header("X-API-TOKEN", "test")
            .content(objectMapper.writeValueAsString(addressRequest))
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
  void createAddressSuccess() throws Exception {
    CreateAddressRequest addressRequest= new CreateAddressRequest();
    addressRequest.setStreet("Jalan Raya");
    addressRequest.setCity("Jakarta");
    addressRequest.setProvince("DKI Jakarta");
    addressRequest.setCountry("Indonesia");
    addressRequest.setPostalCode("12345");

    mockMvc.perform(
        post("/api/contacts/test/addresses")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header("X-API-TOKEN", "test")
            .content(objectMapper.writeValueAsString(addressRequest))
    ).andExpectAll(
        status().isOk()
    ).andDo(result -> {
      String contentAsString = result.getResponse().getContentAsString();
      WebResponse<AddressResponse> response = objectMapper.readValue(contentAsString, new TypeReference<>() {
      });

      assertNull(response.getErrors());
      assertNotNull(response.getData());
      assertEquals(addressRequest.getCity(), response.getData().getCity());
      assertEquals(addressRequest.getStreet(), response.getData().getStreet());
      assertEquals(addressRequest.getProvince(), response.getData().getProvince());
      assertEquals(addressRequest.getCountry(), response.getData().getCountry());
      assertEquals(addressRequest.getPostalCode(), response.getData().getPostalCode());

      assertTrue(addressRepository.existsById(response.getData().getId()));
    });
  }
}