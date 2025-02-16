package classes.string;

public class StringBuilderApp {
  public static void main(String[] args) {

    String name = "Eko";

    name = name + " " + "Kurniawan";
    name = name + " " + "Khannedy";

    System.out.println(name);

    StringBuilder builder = new StringBuilder();
    builder.append("Eko");
    builder.append(" ");
    builder.append("Kurniawan");
    builder.append(" ");
    builder.append("Khannedy");

    String nameBuilder = builder.toString();

    System.out.println(nameBuilder);
  }
}
