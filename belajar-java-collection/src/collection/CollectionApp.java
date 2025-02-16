package collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionApp {
  public static void main(String[] args) {

    Collection<String> collection = new ArrayList<>();

    collection.add("Eko");
    collection.add("Kurniawan");
    collection.add("Khannedy");
    collection.addAll(List.of("Charisto", "Marc", "Gybran"));

    for (var value : collection) {
      System.out.println(value);
    }

    collection.remove("Eko");
    collection.removeAll(List.of("Kurniawan", "Khannedy"));

    System.out.println("===AFTER REMOVE===");
    for (var value : collection) {
      System.out.println(value);
    }

    System.out.println("===CONTAINS===");
    System.out.println(
        collection.contains("Kurniawan")
    );

    System.out.println(
        collection.containsAll(List.of("Charisto", "Marc", "Gybran"))
    );


  }
}
