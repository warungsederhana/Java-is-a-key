package programmerzamannow.springmvc.controllers;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void createPerson() throws Exception {
    mockMvc.perform(
        post("/person")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .param("firstName", "Eko")
            .param("middleName", "Kurniawan")
            .param("lastName", "Khannedy")
            .param("email", "test@gmail.com")
            .param("phone", "0812345678")
            .param("address.street", "Jl. Belum Jadi")
            .param("address.city", "Jakarta")
            .param("address.country", "Indonesia")
            .param("address.postalCode", "13890")
            .param("hobbies[0]", "Coding")
            .param("hobbies[1]", "Reading")
            .param("hobbies[2]", "Traveling")
            .param("socialMedias[0].name", "Facebook")
            .param("socialMedias[0].location", "https://facebook.com/programmerzamannow")
            .param("socialMedias[1].name", "Instagram")
            .param("socialMedias[1].location", "https://instagram.com/programmerzamannow")
    ).andExpectAll(
        status().isCreated(),
        content().string(Matchers.containsString("Success create person Eko Kurniawan Khannedy " +
            "with email test@gmail.com and phone 0812345678 with address " +
            "Jl. Belum Jadi, Jakarta, Indonesia 13890"))
    );
  }
}