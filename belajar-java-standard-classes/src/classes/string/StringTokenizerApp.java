package classes.string;

import java.util.StringTokenizer;

public class StringTokenizerApp {
  public static void main(String[] args) {

    String name = "Eko Kurniawan Khannedy";
    StringTokenizer tokenizer = new StringTokenizer(name, " ");

    while (tokenizer.hasMoreTokens()) {
      String result = tokenizer.nextToken();
      System.out.println(result);
    }
  }
}
