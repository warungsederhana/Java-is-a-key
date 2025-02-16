package programmerzamannow.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import programmerzamannow.servlet.model.SayHelloRequest;
import programmerzamannow.servlet.util.JsonUtil;

import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = {"/api/say-hello"})
public class ApiServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    SayHelloRequest sayHelloRequest = JsonUtil.getObjectMapper().readValue(req.getReader(), SayHelloRequest.class);
    String sayHello = "Hello " + sayHelloRequest.getFirstName() + " " + sayHelloRequest.getLastName();

    Map<String, String> resposnse = Map.of(
        "data", sayHello
    );

    String jsonResponse = JsonUtil.getObjectMapper().writeValueAsString(resposnse);
    resp.setContentType("application/json");
    resp.getWriter().println(jsonResponse);
  }
}
