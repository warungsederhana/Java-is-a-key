package programmerzamannow.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JavaBeanTest {

  @Test
  void createJsonFromObject() {
    Person person = new Person();
    person.setId("1");
    person.setName("Eko");
    person.setHobbies(List.of("Reading", "Coding", "Traveling"));

    Address address = new Address();
    address.setStreet("Jl. Merdeka");
    address.setCity("Jakarta");
    address.setCountry("Indonesia");

    person.setAddress(address);

    ObjectMapper objectMapper = new ObjectMapper();

    try {
      String json = objectMapper.writeValueAsString(person);

      System.out.println(json);
    } catch (JsonProcessingException e) {
      Assertions.fail(e);
    }
  }

  @Test
  void readObjectFromJson() {
    String json = """
        {
            "id": "1",
            "name": "Eko",
            "hobbies": [
              "Reading",
              "Coding",
              "Traveling"
            ],
            "address": {
              "street": "Jl. Merdeka",
              "city": "Jakarta",
              "country": "Indonesia"
            }
          }
        """;

    ObjectMapper objectMapper = new ObjectMapper();

    try{
      Person person = objectMapper.readValue(json, Person.class);

      System.out.println(person);
    } catch (JsonProcessingException e) {
      Assertions.fail(e);
    }
  }
}
