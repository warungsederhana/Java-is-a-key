package lambda.app;

import lambda.util.StringUtil;

import java.util.function.Function;
import java.util.function.Predicate;

public class MethodReferenceApp {
  public static void main(String[] args) {
//    Predicate<String> predicateIsLowerCase = new Predicate<String>() {
//      @Override
//      public boolean test(String s) {
//        return StringUtil.isLowerCase(s);
//      }
//    };

//    Predicate<String> predicateIsLowerCase = value -> StringUtil.isLowerCase(value);

    Predicate<String> predicateIsLowerCase = StringUtil::isLowerCase;

    System.out.println(predicateIsLowerCase.test("eko"));
    System.out.println(predicateIsLowerCase.test("Eko"));

//    METHOD REFERENCE DI PARAMETER
//    Function<String, String> functionUpper = value -> value.toUpperCase();
    Function<String, String> functionUpper = String::toUpperCase;

    System.out.println(functionUpper.apply("eko"));

  }

  public void run() {
    Predicate<String> runPredicate = this::isLowerCase;

    System.out.println(runPredicate.test("eko"));
  }

  public void run2() {
    MethodReferenceApp app = new MethodReferenceApp();
    Predicate<String> runPredicate = app::isLowerCase;

    System.out.println(runPredicate.test("eko"));
  }



  public boolean isLowerCase(String value) {
    for (char c : value.toCharArray()) {
      if (!Character.isLowerCase(c)) {
        return false;
      }
    }
    return true;
  }
}
