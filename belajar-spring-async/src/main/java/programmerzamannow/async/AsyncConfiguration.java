package programmerzamannow.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
public class AsyncConfiguration {

  // custom executor
  @Bean
  public Executor taskExecutor() {
    return Executors.newVirtualThreadPerTaskExecutor();
  }

  @Bean
  public Executor singleTaskExecutor() {
    return Executors.newSingleThreadExecutor();
  }


  // custom scheduled executor
  @Bean
  public ScheduledExecutorService taskScheduler() {
    // this overrides the default task scheduler at application.properties
    return Executors.newScheduledThreadPool(10);
  }
}
