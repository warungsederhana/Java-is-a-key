package programmerzamannow.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@WebServlet(urlPatterns = {"/form-upload"})
@MultipartConfig
public class FormUploadServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try (InputStream inputStream = FormUploadServlet.class.getResourceAsStream("/html/form-upload.html")) {
      if (inputStream == null) {
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        resp.getWriter().println("File not found");
        return;
      }
      String html = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
      resp.getWriter().println(html);
    }
  }


  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String name = req.getParameter("name");
    Part profile = req.getPart("profile");

    Path uploadLocation = Path.of("uploads/" + UUID.randomUUID() + profile.getSubmittedFileName());
    Files.copy(profile.getInputStream(), uploadLocation);

    String html = """
            <html>
            <body>
              Name: $name<br>
              Profile: <img width="400px" height="400px" src="/download?file=$profile"/> <br>
            </body>
          </html>
        """
        .replace("$name", name)
        .replace("$profile", uploadLocation.getFileName().toString());

    resp.getWriter().println(html);
  }
}
