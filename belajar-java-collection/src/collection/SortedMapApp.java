package collection;

import data.Person;

import java.sql.SQLOutput;
import java.util.Collections;
import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;

public class SortedMapApp {
  public static void main(String[] args) {
    // COMPARABLE
    System.out.println("=== COMPARABLE");
    SortedMap<String, String> sortedMap = new TreeMap<>();

    sortedMap.put("Eko", "Eko");
    sortedMap.put("Budi", "Budi");
    sortedMap.put("Joko", "Joko");

    for (var key : sortedMap.keySet()) {
      System.out.println(key);
    }

    // COMPARATOR
    System.out.println("=== COMPARATOR (descending)");
    Comparator<Person> personComparator = new Comparator<Person>() {
      @Override
      public int compare(Person o1, Person o2) {
        // ASCENDING
        // return o1.getName().compareTo(o2.getName());
        // DESCENDING
        return o2.getName().compareTo(o1.getName());
      }
    };
    SortedMap<Person, String> sortedPerson = new TreeMap<Person, String>(personComparator);

    sortedPerson.put(new Person("Eko"), "Eko");
    sortedPerson.put(new Person("Budi"), "Budi");
    sortedPerson.put(new Person("Joko"), "Joko");

    for (var key : sortedPerson.keySet()) {
      System.out.println(key.getName());
    }

    // IMMUATABLE
    System.out.println("=== IMMUATABLE");

    SortedMap<String, String> empty = Collections.emptySortedMap();
    SortedMap<String, String> immutable = Collections.unmodifiableSortedMap(sortedMap);

    // immutable.put("Rudi", "Rudi"); // ERROR: UnsupportedOperationException
    System.out.println(empty);
    System.out.println(immutable);
  }
}
