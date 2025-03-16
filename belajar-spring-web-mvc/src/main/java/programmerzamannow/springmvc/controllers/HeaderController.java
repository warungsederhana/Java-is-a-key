package programmerzamannow.springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HeaderController {

  @GetMapping(path = "/header/token")
  @ResponseBody
  public String header(@RequestHeader(name = "X-TOKEN") String token) {
    if ("Eko".equals(token)) {
      return "OK";
    } else {
      return "UNAUTHORIZED";
    }
  }
}
