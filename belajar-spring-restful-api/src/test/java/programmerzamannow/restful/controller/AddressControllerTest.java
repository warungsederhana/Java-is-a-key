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
import programmerzamannow.restful.model.address.UpdateAddressRequest;
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

  @Test
  void getAddressNotFound() throws Exception {
    mockMvc.perform(
        get("/api/contacts/test/addresses/not-found")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header("X-API-TOKEN", "test")
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
  void getAddressSuccess() throws Exception {
    Contact contact = contactRepository.findById("test").orElseThrow();
    assertNotNull(contact);

    Address address = new Address();
    address.setContact(contact);
    address.setId(UUID.randomUUID().toString());
    address.setStreet("Jalan Raya");
    address.setCity("Jakarta");
    address.setProvince("DKI Jakarta");
    address.setCountry("Indonesia");
    address.setPostalCode("12345");
    addressRepository.save(address);

    mockMvc.perform(
        get("/api/contacts/test/addresses/"+address.getId())
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header("X-API-TOKEN", "test")
    ).andExpectAll(
        status().isOk()
    ).andDo(result -> {
      String contentAsString = result.getResponse().getContentAsString();
      WebResponse<AddressResponse> response = objectMapper.readValue(contentAsString, new TypeReference<>() {
      });

      assertNull(response.getErrors());
      assertNotNull(response.getData());

      assertEquals(address.getId(), response.getData().getId());
      assertEquals(address.getCity(), response.getData().getCity());
      assertEquals(address.getStreet(), response.getData().getStreet());
      assertEquals(address.getProvince(), response.getData().getProvince());
      assertEquals(address.getCountry(), response.getData().getCountry());
      assertEquals(address.getPostalCode(), response.getData().getPostalCode());
    });
  }

  @Test
  void updateAddressBadRequest() throws Exception {
    UpdateAddressRequest request= new UpdateAddressRequest();
    request.setCountry("");

    mockMvc.perform(
        put("/api/contacts/test/addresses/not-found")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header("X-API-TOKEN", "test")
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
  void updateAddressSuccess() throws Exception {
    Contact contact = contactRepository.findById("test").orElseThrow();
    assertNotNull(contact);

    Address address = new Address();
    address.setContact(contact);
    address.setId(UUID.randomUUID().toString());
    address.setStreet("Jalan Raya");
    address.setCity("Jakarta");
    address.setProvince("DKI Jakarta");
    address.setCountry("Indonesia");
    address.setPostalCode("12345");
    addressRepository.save(address);

    UpdateAddressRequest request= new UpdateAddressRequest();
    request.setStreet("Jalan Raya Updated");
    request.setCity("Jakarta Updated");
    request.setProvince("DKI Jakarta Updated");
    request.setCountry("Indonesia Updated");
    request.setPostalCode("67890");

    mockMvc.perform(
        put("/api/contacts/test/addresses/" + address.getId())
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header("X-API-TOKEN", "test")
            .content(objectMapper.writeValueAsString(request))
    ).andExpectAll(
        status().isOk()
    ).andDo(result -> {
      String contentAsString = result.getResponse().getContentAsString();
      WebResponse<AddressResponse> response = objectMapper.readValue(contentAsString, new TypeReference<>() {
      });

      assertNull(response.getErrors());
      assertNotNull(response.getData());
      assertEquals(request.getCity(), response.getData().getCity());
      assertEquals(request.getStreet(), response.getData().getStreet());
      assertEquals(request.getProvince(), response.getData().getProvince());
      assertEquals(request.getCountry(), response.getData().getCountry());
      assertEquals(request.getPostalCode(), response.getData().getPostalCode());

      assertTrue(addressRepository.existsById(response.getData().getId()));
    });
  }

  @Test
  void deleteAddressNotFound() throws Exception {
    mockMvc.perform(
        delete("/api/contacts/test/addresses/not-found")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header("X-API-TOKEN", "test")
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
  void deleteAddressSuccess() throws Exception {
    Contact contact = contactRepository.findById("test").orElseThrow();
    assertNotNull(contact);

    Address address = new Address();
    address.setContact(contact);
    address.setId(UUID.randomUUID().toString());
    address.setStreet("Jalan Raya");
    address.setCity("Jakarta");
    address.setProvince("DKI Jakarta");
    address.setCountry("Indonesia");
    address.setPostalCode("12345");
    addressRepository.save(address);

    mockMvc.perform(
        delete("/api/contacts/test/addresses/"+address.getId())
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header("X-API-TOKEN", "test")
    ).andExpectAll(
        status().isOk()
    ).andDo(result -> {
      String contentAsString = result.getResponse().getContentAsString();
      WebResponse<String> response = objectMapper.readValue(contentAsString, new TypeReference<>() {
      });

      assertNull(response.getErrors());
      assertNotNull(response.getData());

      assertEquals("OK", response.getData());
      assertFalse(addressRepository.existsById(address.getId()));
    });
  }
}