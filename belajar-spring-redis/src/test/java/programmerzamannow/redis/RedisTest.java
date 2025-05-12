package programmerzamannow.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.time.Duration;

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
}
