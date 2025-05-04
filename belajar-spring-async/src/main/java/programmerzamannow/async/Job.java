package programmerzamannow.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Component
@Slf4j
public class Job {

  private AtomicLong counter = new AtomicLong(0);

  @Scheduled(timeUnit = TimeUnit.SECONDS, initialDelay = 2, fixedDelay = 2)
  public void runJob() {
    Long value = counter.incrementAndGet();
    log.info("Job running {} on thread {}", value, Thread.currentThread());
  }

  public long getCounter() {
    return counter.get();
  }

  @Scheduled(cron = "*/2 * * * * *")
  public void cron() {
    log.info("run cron job {}", Thread.currentThread());
  }
}
