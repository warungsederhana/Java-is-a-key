package lambda.app;

import lambda.SimpleAction;

public class SImpleActionApp {
  public static void main(String[] args) {

//    SimpleAction simpleAction1 = new SimpleAction() {
//      @Override
//      public String action(String name) {
//        return "Hello World!";
//      }
//    };
//
//    System.out.println(simpleAction1.action("Eko"));
//
//
////    lambda
//    SimpleAction simpleAction2 = (String name) -> {
//      return "Hello World!";
//    };
//    System.out.println(simpleAction2.action("Eko"));

    SimpleAction simpleAction1 = (String name) -> {
      return "Hello " + name;
    };

    SimpleAction simpleAction2 = (name) -> {
      return "Hello " + name;
    };

    SimpleAction simpleAction3 = (String name) -> "Hello " + name;
    SimpleAction simpleAction4 = (name) -> "Hello " + name;
    SimpleAction simpleAction5 = name -> "Hello " + name;
  }
}
