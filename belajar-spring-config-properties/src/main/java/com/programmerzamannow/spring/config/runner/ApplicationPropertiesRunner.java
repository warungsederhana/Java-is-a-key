package com.programmerzamannow.spring.config.runner;

import com.programmerzamannow.spring.config.properties.ApplicationProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class ApplicationPropertiesRunner implements ApplicationRunner {

  @Autowired
  private ApplicationProperties applicationProperties;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    log.info(applicationProperties.getName());
    log.info(applicationProperties.getVersion().toString());
    log.info(applicationProperties.getIsProduction().toString());
  }
}
