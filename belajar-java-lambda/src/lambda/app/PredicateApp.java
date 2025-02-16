package lambda.app;

import java.util.function.Predicate;

public class PredicateApp {
  public static void main(String[] args) {

//    Predicate<String> predicateCheckLength = new Predicate<String>() {
//      @Override
//      public boolean test(String s) {
//        return s.isBlank();
//      }
//    };

    Predicate<String> predicateCheckBlank = value -> value.isBlank();

    System.out.println(predicateCheckBlank.test(""));
    System.out.println(predicateCheckBlank.test("Eko"));
  }
}
