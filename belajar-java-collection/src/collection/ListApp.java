package collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListApp {
  public static void main(String[] args) {

    List<String> strings = new ArrayList<>();
    List<String> linkedStrings = new LinkedList<>();

    strings.add("Eko");
    strings.add("Kurniawan");
    strings.add("Khannedy");

    strings.set(0, "Budi");

    strings.remove(1);
    System.out.println(strings.get(0));

    for (var value : strings) {
      System.out.println(value);
    }

  }
}
