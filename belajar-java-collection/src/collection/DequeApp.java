package collection;

import java.util.Deque;
import java.util.LinkedList;

public class DequeApp {
  public static void main(String[] args) {

    Deque<String> stack = new LinkedList<>();

    stack.offerLast("Eko");
    stack.offerLast("Kurniawan");
    stack.offerLast("Khannedy");
    stack.offerLast("Charisto");

//    for (var value : stack) {
//      System.out.println(value);
//    }
//    System.out.println("===Hanya diprint size : " + stack.size());

//    pollLast() mengambil data dari belakang sehingga seperti bentuk stack
    System.out.println("IMPLEMENTASI STACK");
    for (String next = stack.pollLast(); next != null; next = stack.pollLast()) {
      System.out.println(next);
    }

    Deque<String> queue = new LinkedList<>();

    queue.offerLast("Eko");
    queue.offerLast("Kurniawan");
    queue.offerLast("Khannedy");
    queue.offerLast("Charisto");

//    pollFirst() mengambil data dari depan sehingga seperti bentuk queue
    System.out.println("IMPLEMENTASI QUEUE");
    for (String next = queue.pollFirst(); next != null; next = queue.pollFirst()) {
      System.out.println(next);
    }
  }
}
