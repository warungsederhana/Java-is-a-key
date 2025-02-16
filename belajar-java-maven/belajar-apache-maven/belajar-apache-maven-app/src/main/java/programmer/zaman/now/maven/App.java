package programmer.zaman.now.maven;

import com.google.gson.Gson;

/**
 * Hello world!
 */
public class  App {
  public static void main(String[] args) {

    Gson gson = new Gson();
    Person person = new Person("Charisto Marc Gybran");

    String json = gson.toJson(person);
    System.out.println(json);
  }
}
