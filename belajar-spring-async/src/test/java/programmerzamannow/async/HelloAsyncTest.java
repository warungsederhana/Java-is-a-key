package programmerzamannow.async;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
@SpringBootTest
class HelloAsyncTest {

  @Autowired
  private HelloAsync helloAsync;

  @Test
  void helloAsync() throws InterruptedException {
    for (int i = 0; i < 50; i++) {
      helloAsync.hello();
    }

    log.info("after call helloAsync");
    Thread.sleep(Duration.ofSeconds(5));
  }

  @Test
  void helloName() throws InterruptedException, ExecutionException {
    Future<String> future = helloAsync.hello("Eko");
    log.info("after call hello Eko");
    String response = future.get();
    log.info("response : {}", response);
  }
}