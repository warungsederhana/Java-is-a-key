package classes;

public class SystemApp {
  public static void main(String[] args) {

    System.out.println(System.currentTimeMillis());
    System.out.println(System.nanoTime());

    System.out.println(System.getenv("HOMEPATH"));
    System.out.println(System.getenv("JAVA_HOME"));
  }
}
