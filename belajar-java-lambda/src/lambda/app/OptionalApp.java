package lambda.app;

import java.util.Optional;

public class OptionalApp {
  public static void main(String[] args) {

    sayHello("Eko");
//    sayHello(null);  // akan ERROR

    optionalSayHello("Eko");
    optionalSayHello(null);

  }

  public static void sayHello(String name) {
    String nameUpper = name.toUpperCase();
    System.out.println("Hello " + nameUpper);
  }

  public static void optionalSayHello(String name) {
//    Optional<String> optionalName = Optional.ofNullable(name);
//    Optional<String> optionalNameUpper = optionalName.map(String::toUpperCase);
//    optionalNameUpper.ifPresent(value -> System.out.println("Hello " + value));
    Optional.ofNullable(name)
        .map(String::toUpperCase)
        .ifPresentOrElse(value -> System.out.println("Hello " + value),
            () -> System.out.println("Hello Stranger"));
  }
}
