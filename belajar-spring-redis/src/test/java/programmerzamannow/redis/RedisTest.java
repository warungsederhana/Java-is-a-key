package programmerzamannow.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RedisTest {

  @Autowired
  private StringRedisTemplate redisTemplate;

  @Test
  void redisTemplate() {
    assertNotNull(redisTemplate);
  }

  @Test
  void string() throws InterruptedException {
    ValueOperations<String, String> operations = redisTemplate.opsForValue();

    operations.set("name", "John", Duration.ofSeconds(2));
    assertEquals("John", operations.get("name"));

    Thread.sleep(Duration.ofSeconds(3));
    assertNull(operations.get("name"));
  }

  @Test
  void list() throws InterruptedException {
    ListOperations<String, String> operations = redisTemplate.opsForList();
    operations.rightPush("names", "John");
    operations.rightPush("names", "Jane");
    operations.rightPush("names", "Doe");
    operations.rightPush("names", "Smith");

//    Thread.sleep(Duration.ofSeconds(10));

    assertEquals("John", operations.leftPop("names"));
    assertEquals("Jane", operations.leftPop("names"));
    assertEquals("Doe", operations.leftPop("names"));
    assertEquals("Smith", operations.leftPop("names"));
  }

  @Test
  void set() throws InterruptedException {
    SetOperations<String, String> operations = redisTemplate.opsForSet();
    operations.add("students", "John");
    operations.add("students", "Jane");
    operations.add("students", "Doe");
    operations.add("students", "Smith");
    operations.add("students", "John");
    operations.add("students", "Jane");

    assertEquals(4, operations.size("students"));
    assertThat(operations.members("students"), hasItems("John", "Jane", "Doe", "Smith"));

//    Thread.sleep(Duration.ofSeconds(10));
    redisTemplate.delete("students");
  }

  @Test
  void zSet() throws InterruptedException {
    ZSetOperations<String, String> operations = redisTemplate.opsForZSet();
    operations.add("scores", "John", 90);
    operations.add("scores", "Jane", 80);
    operations.add("scores", "Doe", 70);
    operations.add("scores", "Smith", 60);

//    Thread.sleep(Duration.ofSeconds(20));

    assertEquals("John", operations.popMax("scores").getValue());
    assertEquals("Jane", operations.popMax("scores").getValue());
    assertEquals("Doe", operations.popMax("scores").getValue());
    assertEquals("Smith", operations.popMax("scores").getValue());
  }

  @Test
  void hash() {
    HashOperations<String, Object, Object> operations = redisTemplate.opsForHash();
//    operations.put("user:1", "id", "1");
//    operations.put("user:1", "name", "John");
//    operations.put("user:1", "email", "john@example.com");

    Map<Object, Object> map = new HashMap<>();
    map.put("id", "1");
    map.put("name", "John");
    map.put("email", "john@example.com");

    operations.putAll("user:1", map);

    assertEquals("1", operations.get("user:1", "id"));
    assertEquals("John", operations.get("user:1", "name"));
    assertEquals("john@example.com", operations.get("user:1", "email"));

    redisTemplate.delete("user:1");
  }

  @Test
  void geo() {
    GeoOperations<String, String> operations = redisTemplate.opsForGeo();
    operations.add("sellers", new Point(106.822702, -6.177590), "John");
    operations.add("sellers", new Point(106.820889, -6.174964), "Jane");

    Distance distance = operations.distance("sellers", "John", "Jane", Metrics.KILOMETERS);
    assertEquals(0.3543, distance.getValue());

    GeoResults<RedisGeoCommands.GeoLocation<String>> sellers = operations
        .search("sellers", new Circle(
            new Point(106.821825, -6.175105),
            new Distance(5, Metrics.KILOMETERS)
        ));

    assertEquals(2, sellers.getContent().size());
    assertEquals("John", sellers.getContent().get(0).getContent().getName());
    assertEquals("Jane", sellers.getContent().get(1).getContent().getName());
  }
}
