package programmerzamannow.servlet.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

  private final static ObjectMapper OBJECT_MAPPER= new ObjectMapper();

  public static ObjectMapper getObjectMapper() {
    return OBJECT_MAPPER;
  }
}
