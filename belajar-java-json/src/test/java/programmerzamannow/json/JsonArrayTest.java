package programmerzamannow.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.List;

public class JsonArrayTest {
  @Test
  void createJsonArray() {
    List<String> hobbies = List.of("Reading", "Coding", "Traveling");

    ObjectMapper objectMapper = new ObjectMapper();

    try {
      String json = objectMapper.writeValueAsString(hobbies);

      System.out.println(json);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void readJsonArray() {
    String json = """
        ["Reading", "Coding", "Traveling"]
        """;

    ObjectMapper objectMapper = new ObjectMapper();
    try {
      List<String> hobbies = objectMapper.readValue(json, new TypeReference<List<String>>() {
        @Override
        public Type getType() {
          return super.getType();
        }

        @Override
        public int compareTo(TypeReference<List<String>> o) {
          return super.compareTo(o);
        }
      });

      Assertions.assertEquals(3, hobbies.size());
      Assertions.assertTrue(hobbies.contains("Reading"));
      Assertions.assertTrue(hobbies.contains("Coding"));
      Assertions.assertTrue(hobbies.contains("Traveling"));

      System.out.println(hobbies);
    } catch (JsonProcessingException e) {
      Assertions.fail(e);
    }
  }
}
