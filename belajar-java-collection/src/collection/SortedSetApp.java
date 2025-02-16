package collection;

import data.Person;
import data.PersonComparator;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

public class SortedSetApp {
  public static void main(String[] args) {

    // SortedSet<Person> people = new TreeSet<>(new PersonComparator());
    SortedSet<Person> people = new TreeSet<>(new PersonComparator().reversed());

    people.add(new Person("Eko"));
    people.add(new Person("Kurniawan"));
    people.add(new Person("Khannedy"));

    for (var person: people) {
      System.out.println(person.getName());
    }

    // Immutable SortedSet
    SortedSet<Person> people1 = Collections.unmodifiableSortedSet(people);
    // people1.add(new Person("Budi")); // Error

  }
}
