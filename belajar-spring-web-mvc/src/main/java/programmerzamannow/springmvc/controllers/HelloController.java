package programmerzamannow.springmvc.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import programmerzamannow.springmvc.services.HelloService;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Controller
public class HelloController {

    @Autowired
    private HelloService helloService;

//  @RequestMapping(path = "/hello", method = RequestMethod.GET)
//  @GetMapping(path = "/hello")
//  public void helloWorld(HttpServletRequest request, HttpServletResponse response) throws IOException {
//    String name = request.getParameter("name");
//    String responseBody = helloService.hello(name);
//    response.getWriter().println(responseBody);
//  }

    @GetMapping(path = "/hello")
    public void helloWorld(@RequestParam(name = "name", required = false) String name, HttpServletResponse response) throws IOException {
        String responseBody = helloService.hello(name);
        response.getWriter().println(responseBody);
    }

    @GetMapping(path = "/web/hello")
    public ModelAndView hello(@RequestParam(name = "name", required = false) String name, HttpServletResponse response) throws IOException {
        if (Objects.isNull(name)) {
            return new ModelAndView("redirect:/web/hello?name=Guest");
        }

        return new ModelAndView("hello", Map.of(
                "title", "Belajar View",
                "name", name
        ));
    }
}
