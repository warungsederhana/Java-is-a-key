package programmer.zaman.now.test;

public class Calculator {

  public Integer add(Integer first, Integer second) {
    return first + second;
  }

  public Integer devide(Integer first, Integer second) {
    if (second == 0) {
      throw new IllegalArgumentException("Can't devide by zero");
    }
    return first / second;
  }

}
