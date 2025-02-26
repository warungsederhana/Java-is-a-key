package programmerzamannow.springmvc.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class DateController {

  private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

  @GetMapping(path = "/date")
   public void getDate(@RequestParam(name = "date")Date date,
                      HttpServletResponse response) throws Exception {
    response.getWriter().println("Date : " + simpleDateFormat.format(date));
  }
}
