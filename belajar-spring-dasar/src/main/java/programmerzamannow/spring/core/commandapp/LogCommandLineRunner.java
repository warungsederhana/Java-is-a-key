package programmerzamannow.spring.core.commandapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
public class LogCommandLineRunner implements CommandLineRunner {
  @Override
  public void run(String... args) throws Exception {
    log.info("LOG COMMAND LINE RUNNER : {}", Arrays.toString(args));
  }
}
