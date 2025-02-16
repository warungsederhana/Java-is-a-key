package classes.string;

import java.util.StringJoiner;

public class StringJoinerApp {
  public static void main(String[] args) {

    StringJoiner joiner = new StringJoiner(", ", "[", "]");
    joiner.add("Eko");
    joiner.add("Kurniawan");
    joiner.add("Khannedy");

    String nameJoined = joiner.toString();
    System.out.println(nameJoined);
  }
}
