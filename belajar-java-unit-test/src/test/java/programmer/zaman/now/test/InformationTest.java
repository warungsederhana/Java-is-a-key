package programmer.zaman.now.test;

import org.junit.jupiter.api.*;

@DisplayName("Information Test")
public class InformationTest {

  @Test
  @DisplayName("this is test 1")
  @Tags({
      @Tag("one"),
      @Tag("two"),
  })
  void test1(TestInfo info) {
    System.out.println(info.getDisplayName());
    System.out.println(info.getTags());
    System.out.println(info.getTestClass().orElse(null));
    System.out.println(info.getTestMethod().orElse(null));
  }
}
