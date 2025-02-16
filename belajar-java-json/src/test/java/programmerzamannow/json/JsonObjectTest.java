package programmerzamannow.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class JsonObjectTest {
  @Test
  void createJson() {
    Map<String, Object> person = Map.of(
        "firstName", "Gybran",
        "lastName", "Raka",
        "age", 24,
        "married", false,
        "address", Map.of(
            "street", "Jl. Merdeka",
            "city", "Jakarta",
            "country", "Indonesia"
        )
    );

    ObjectMapper objectMapper = new ObjectMapper();

    try {
      String json = objectMapper.writeValueAsString(person);

      System.out.println(json);
    } catch (JsonProcessingException e) {
      Assertions.fail(e);
    }
  }

  @Test
  void readJson() {
    String json = """
        {
          "age": 24,
          "married": false,
          "lastName": "Raka",
          "firstName": "Gybran",
          "address": {
            "country": "Indonesia",
            "city": "Jakarta",
            "street": "Jl. Merdeka"
          }
        }
        """;

    ObjectMapper objectMapper = new ObjectMapper();
    try {
      Map<String, Object> person = objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {
      });

      Assertions.assertEquals("Gybran", person.get("firstName"));
      Assertions.assertEquals("Raka", person.get("lastName"));
    } catch (JsonProcessingException e) {
      Assertions.fail(e);
    }
  }
}
