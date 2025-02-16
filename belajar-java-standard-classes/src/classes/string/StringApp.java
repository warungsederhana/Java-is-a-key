package classes.string;

public class StringApp {
  public static void main(String[] args) {
    String name = "Eko Kurniawan Khannedy";
    String nameLower = name.toLowerCase();
    String nameUpper = name.toUpperCase();

    System.out.println(name);
    System.out.println(nameLower);
    System.out.println(nameUpper);

    System.out.println("Panjang string name: " + name.length());
    System.out.println(name.startsWith("Eko"));
    System.out.println(name.endsWith("Khannedy"));

    String[] names = name.split(" ");
    for(var val : names) {
      System.out.println(val);
    }


  }
}
