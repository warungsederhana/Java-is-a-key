package collection;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueApp {
  public static void main(String[] args) {

    Queue<String> queue = new ArrayDeque<>();
//    Queue<String>  queue = new PriorityQueue<>(); // data akan diurutkan

    queue.add("Eko");
    queue.add("Kurniawan");
    queue.add("Khannedy");
    System.out.println("=== Size : " + queue.size());

//    for (int i = 0; i < 10; i++) {
//      queue.offer(String.valueOf(i));
//    }

    for (String next = queue.poll(); next != null; next = queue.poll()) {
      System.out.println(next);
    }
    System.out.println("=== Size : " + queue.size());

  }
}
