package programmer.zaman.now.aop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HelloService {

  public String hello(String name) {
//    tidak perlu karena sudah menggunakan aspectj pada kelas LogAspect.java
//    log.info("Call HelloService.hello with name: {}", name);
    return "Hello " + name;
  }

  public String helloFullName(String firstName, String lastName) {
    return "Hello " + firstName + " " + lastName;
  }

  public String bye(String name) {
//    tidak perlu karena sudah menggunakan aspectj pada kelas LogAspect.java
//    log.info("Call HelloService.bye with name: {}", name);
    return "Bye " + name;
  }

  public void test() {
    log.info("Call HelloService.test()");
  }
}
