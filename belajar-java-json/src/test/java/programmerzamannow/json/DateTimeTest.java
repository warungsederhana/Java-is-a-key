package programmerzamannow.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DateTimeTest {

  @Test
  void dateTime() {
    ObjectMapper objectMapper = new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true);

    Person person = new Person();
    person.setId("1");
    person.setName("Eko");
    person.setHobbies(List.of("Reading", "Coding", "Traveling"));
    person.setCreatedAt(new Date());
    person.setUpdatedAt(new Date());

    Address address = new Address();
    address.setStreet("Jl. Merdeka");
    address.setCity("Jakarta");
    address.setCountry("Indonesia");

    person.setAddress(address);

    try {
      String json = objectMapper.writeValueAsString(person);

      System.out.println(json);
    } catch (JsonProcessingException e) {
      Assertions.fail(e);
    }
  }

  @Test
  void dateFormat() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    ObjectMapper objectMapper = new ObjectMapper()
        .configure(SerializationFeature.INDENT_OUTPUT, true)
        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        .setDateFormat(dateFormat);

    Person person = new Person();
    person.setId("1");
    person.setName("Eko");
    person.setHobbies(List.of("Reading", "Coding", "Traveling"));
    person.setCreatedAt(new Date());
    person.setUpdatedAt(new Date());

    Address address = new Address();
    address.setStreet("Jl. Merdeka");
    address.setCity("Jakarta");
    address.setCountry("Indonesia");

    person.setAddress(address);

    try {
      String json = objectMapper.writeValueAsString(person);

      System.out.println(json);
    } catch (JsonProcessingException e) {
      Assertions.fail(e);
    }
  }
}
