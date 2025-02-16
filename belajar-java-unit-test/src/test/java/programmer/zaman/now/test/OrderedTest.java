package programmer.zaman.now.test;

import org.junit.jupiter.api.*;

//@TestMethodOrder(MethodOrderer.MethodName.class)
//@TestMethodOrder(MethodOrderer.Random.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)// default is PER_METHOD
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderedTest {

  private Integer counter = 0;

  @Test
  @Order(2)
  void test3() {
    counter++;
    System.out.println(counter);
  }

  @Test
  @Order(1)
  void test2() {
    counter++;
    System.out.println(counter);
  }

  @Test
  @Order(3)
  void test1() {
    counter++;
    System.out.println(counter);
  }
}
