package programmerzamannow.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@WebServlet(urlPatterns = {"/form"})
public class FormServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try (InputStream inputStream = FormServlet.class.getResourceAsStream("/html/form.html")) {
      if (inputStream == null) {
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        resp.getWriter().println("File not found");
        return;
      }
      String html = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
      resp.getWriter().println(html);
    }
  }

//  This code is not working
//  @WebServlet(urlPatterns = {"/form"})
//  public class FormServlet extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//      Path path = Path.of(FormServlet.class.getResource("/html/form.html").getPath());
//      String html = Files.readString(path);
//      resp.getWriter().println(html);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//      String firstName = req.getParameter("firstName");
//      String lastName = req.getParameter("lastName");
//      String fullName = firstName + " " + lastName;
//      String respose = "Hello " + fullName;
//      resp.getWriter().println(respose);
//    }
//  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String firstName = req.getParameter("firstName");
    String lastName = req.getParameter("lastName");
    String fullName = firstName + " " + lastName;
    String response = "Hello " + fullName;
    resp.getWriter().println(response);
  }
}
