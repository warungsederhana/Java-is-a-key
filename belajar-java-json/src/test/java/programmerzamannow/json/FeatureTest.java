package programmerzamannow.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FeatureTest {

  @Test
  void mapperFeature() {
    ObjectMapper objectMapper = new ObjectMapper()
        // jika tidak ada configure ini, maka akan error karena case sensitive
        .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

    String json = """
        {
          "ID" : "1",
          "Name" : "Eko"
        }
        """;

    try {
      Person person = objectMapper.readValue(json, Person.class);

      Assertions.assertEquals("1", person.getId());
      Assertions.assertEquals("Eko", person.getName());
    } catch (JsonProcessingException e) {
      Assertions.fail(e);
    }
  }

  @Test
  void deserializationFeature() {
    ObjectMapper objectMapper = new ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

    String json = """
        {
          "id" : "1",
          "name" : "Eko",
          "age" : 24,
          "hobbies": "Coding"
        }
        """;

    try {
      Person person = objectMapper.readValue(json, Person.class);

      System.out.println(person);
      Assertions.assertEquals("1", person.getId());
      Assertions.assertEquals("Eko", person.getName());
    } catch (JsonProcessingException e) {
      Assertions.fail(e);
    }
  }

  @Test
  void serializationFeature() {
    Person person = new Person();
    person.setId("1");
    person.setName("Eko");
    person.setHobbies(List.of("Reading", "Coding", "Traveling"));

    Address address = new Address();
    address.setStreet("Jl. Merdeka");
    address.setCity("Jakarta");
    address.setCountry("Indonesia");

    person.setAddress(address);

    ObjectMapper objectMapper = new ObjectMapper()
        .configure(SerializationFeature.INDENT_OUTPUT, true);

    try {
      String json = objectMapper.writeValueAsString(person);

      System.out.println(json);
    } catch (JsonProcessingException e) {
      Assertions.fail(e);
    }
  }

  @Test
  void serializationInclusion() {
    ObjectMapper objectMapper = new ObjectMapper()
        .configure(SerializationFeature.INDENT_OUTPUT, true)
        .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    Person person = new Person();
    person.setId("1");
    person.setName("Eko");

    try {
      String json = objectMapper.writeValueAsString(person);

      System.out.println(json);
    } catch (JsonProcessingException e) {
      Assertions.fail(e);
    }
  }
}

