package classes;

import java.util.Arrays;

public class ArraysApp {
  public static void main(String[] args) {
    int[] numbers = {2, 3, 12, 23, 5, 2, 56, 34, 123, 36, 764};

    Arrays.sort(numbers);

    System.out.println(Arrays.toString(numbers));

    System.out.println(Arrays.binarySearch(numbers, 56));
    System.out.println(Arrays.binarySearch(numbers, 36));
    System.out.println(Arrays.binarySearch(numbers, 100));

    int[] result = Arrays.copyOf(numbers, 5);
    System.out.println(Arrays.toString(result));

    int[] result2 = Arrays.copyOfRange(numbers, 5, numbers.length - 1);
    System.out.println(Arrays.toString(result2));

    int[] result3 = numbers;
    System.out.println(Arrays.toString(result3));

  }
}
