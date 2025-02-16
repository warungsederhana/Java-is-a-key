package collection;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class SetApp {
  public static void main(String[] args) {

    // Set<String> names = new HashSet<>();
    Set<String> names = new LinkedHashSet<>();

    names.add("Eko");
    names.add("Kurniawan");
    names.add("Khannedy");
    names.add("Eko");
    names.add("Kurniawan");
    names.add("Khannedy");

    // Output: Eko, Kurniawan, Khannedy (urutan tidak tentu jika menggunakan HashSet)
    // Set tidak akan menambahkan data yang sama
    for (var name: names) {
      System.out.println(name);
    }

  }
}
