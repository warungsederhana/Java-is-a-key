package lambda.app;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class RemoveIfApp {
  public static void main(String[] args) {

    List<String> names = new ArrayList<>();
    names.addAll(List.of("Eko", "Kurniawan", "Khannedy"));

    // remove if for loop
    // solusi gagal karena data yang sesuai dengan kondisi ada yang tidak terhapus
//    for (var name : names) {
//      if (name.length() > 5) {
//        names.remove(name);
//      }
//    }

    // remove if anonymous class
    names.removeIf(new Predicate<String>() {
      @Override
      public boolean test(String s) {
        return s.length() > 5;
      }
    });

    // remove if lambda
    names.removeIf(value -> value.length() > 5);

  }
}
