package programmer.zaman.now.test;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import programmer.zaman.now.test.resolver.RandomParameterResolver;

import java.util.List;
import java.util.Random;

public class RandomCalculatorTest extends AbstractCalculatorTest {

  @Test
  void testRandom(Random random) {
    int a = random.nextInt();
    int b = random.nextInt();

    int result = calculator.add(a, b);
    int expected = a + b;

    Assertions.assertEquals(expected, result);
  }

  @DisplayName("Test Random Repeat")
  @RepeatedTest(value = 10, name = "{displayName} ke {currentRepetition} dari {totalRepetitions}")
  void testRandomRepeat(Random random) {
    int a = random.nextInt();
    int b = random.nextInt();

    int result = calculator.add(a, b);
    int expected = a + b;

    Assertions.assertEquals(expected, result);
  }

  @DisplayName("Test Random Repeat Info")
  @RepeatedTest(value = 10, name = "{displayName}")
  void testRandomRepeatInfo(TestInfo info, Random random, RepetitionInfo repetitionInfo) {
    System.out.println(info.getDisplayName() + " ke " + repetitionInfo.getCurrentRepetition() + " dari " + repetitionInfo.getTotalRepetitions());
    int a = random.nextInt();
    int b = random.nextInt();

    int result = calculator.add(a, b);
    int expected = a + b;

    Assertions.assertEquals(expected, result);
  }

  @DisplayName("Test Random Parameter")
  @ParameterizedTest(name = "{displayName} dengan parameter {0}")
  @ValueSource(ints = {10, 20, 30, 40, 50})
  void testWithParameters(int value) {
    var result = calculator.add(value, value);
    var expected = value + value;

    Assertions.assertEquals(expected, result);
  }

  public static List<Integer> parameterSource() {
    return List.of(10, 20, 30, 40, 50);
  }

  @DisplayName("Test Random Parameter")
  @ParameterizedTest(name = "{displayName} dengan parameter {0}")
  @MethodSource({"parameterSource"})
  void testWithMethodSource(Integer value) {
    var result = calculator.add(value, value);
    var expected = value + value;

    Assertions.assertEquals(expected, result);
  }

}
