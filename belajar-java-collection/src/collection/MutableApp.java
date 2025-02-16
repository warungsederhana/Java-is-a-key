package collection;

import data.Person;

import java.util.List;

public class MutableApp {
  public static void main(String[] args) {

    Person person = new Person("Eko");

    person.addHobby("Game");
    person.addHobby("Coding");

    doSomethingWithHobbies(person.getHobbies());

    for(var hobby : person.getHobbies()) {
      System.out.println(hobby);
    }

  }

  public static void  doSomethingWithHobbies(List<String> hobbies) {
    // Tidak aman karena kita bisa mengubah data hobbies
    // Akan ERROR karena return getHobbies() adalah Collections.unmodifiableList(hobbies)
    hobbies.add("New Hobby");
  }
}
