package programmerzamannow.springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderController {

  @GetMapping(path = "/order/{orderId}/product/{productId}")
  @ResponseBody
  public String order(@PathVariable("orderId") String orderId, @PathVariable("productId") String productId) {
    return "Order " + orderId + " for Product " + productId + " has been created";
  }
}
