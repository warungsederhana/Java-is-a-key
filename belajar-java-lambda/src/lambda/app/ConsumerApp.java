package lambda.app;

import java.util.function.Consumer;

public class ConsumerApp {
  public static void main(String[] args) {

//    Consumer<String> consumer = new Consumer<String>() {
//      @Override
//      public void accept(String s) {
//        System.out.println(s);
//      }
//    };

    Consumer<String> consumer = s -> System.out.println(s);

    consumer.accept("Eko Kurniawan Khannedy");
  }
}
