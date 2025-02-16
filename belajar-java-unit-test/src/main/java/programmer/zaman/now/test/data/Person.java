package programmer.zaman.now.test.data;

import java.util.Objects;

public class Person {

  private String id;
  private String name;

  public Person(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Person person = (Person) o;
    return Objects.equals(id, person.id) && Objects.equals(name, person.name);
  }

  @Override
  public int hashCode() {
    int result = Objects.hashCode(id);
    result = 31 * result + Objects.hashCode(name);
    return result;
  }

  @Override
  public String toString() {
    return "Person{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        '}';
  }
}
