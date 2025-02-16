package collection;

import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class SpliteratorApp {
  public static void main(String[] args) {

    List<String> list = List.of("Eko", "Kurniawan", "Khannedy", "Programmer", "Zaman", "Now", "Charisto", "Marc", "Gybran");

    Spliterator<String> spliterator = list.spliterator();
    Spliterator<String> spliterator1 = spliterator.trySplit();

    System.out.println(spliterator.estimateSize());
    System.out.println(spliterator1.estimateSize());

    spliterator.forEachRemaining(new Consumer<String>() {
      @Override
      public void accept(String s) {
        System.out.println(s);
      }
    });

    spliterator1.forEachRemaining(new Consumer<String>() {
      @Override
      public void accept(String s) {
        System.out.println(s);
      }
    });
  }
}
