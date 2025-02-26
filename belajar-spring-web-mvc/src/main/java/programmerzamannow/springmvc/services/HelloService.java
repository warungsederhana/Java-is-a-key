package programmerzamannow.springmvc.services;

import org.springframework.stereotype.Service;

@Service
public class HelloService implements IHelloService {

  @Override
  public String hello(String name) {
    if(name == null) {
      name = "Guest";
    }
    return "Hello " + name;
  }
}
