package programmer.zaman.now.test;

import org.junit.jupiter.api.*;
import org.opentest4j.TestAbortedException;
import programmer.zaman.now.test.generator.SimpleDisplayNameGenerator;

import static org.junit.jupiter.api.Assumptions.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(SimpleDisplayNameGenerator.class)
// @DisplayName("Calculator Test")
public class CalculatorTest {

  private Calculator calculator = new Calculator();

  @BeforeAll // HARUS STATIC
  public static void beforeAll() {
    System.out.println("==== BEFORE ALL");
  }

  @AfterAll // HARUS STATIC
  public static void afterAll() {
    System.out.println("==== AFTER ALL");
  }

  @BeforeEach
  public void setUp() {
    System.out.println("==== Before Each ====");
  }

  @AfterEach
  public void tearDown() {
    System.out.println("==== After Each ====");
  }

  @Test
  // @DisplayName("Test Add Success")
  public void testAddSuccess() {
    var result = calculator.add(10, 10);
    assertEquals(20, result);
  }

  @Test
  // @DisplayName("Test Devide Success")
  public void testDevideSuccess() {
    var result = calculator.devide(100, 10);
    assertEquals(10, result);
  }

  @Test
  // @DisplayName("Test Devide Failed")
  // @Disabled //Uncomment this line to disable this test
  public void testDevideFailed() {
    assertThrows(IllegalArgumentException.class, () -> {
      calculator.devide(100, 0);
    });
  }

  @Test
  @Disabled
  public void commingSoon(){}

  @Test
  public void testAborted() {
    var profile = System.getenv("PROFILE");

    if (!"DEV".equals(profile)) {
      throw new TestAbortedException("Test Aborted because not in DEV environment");
    }

    // test code for DEV environment
  }

  @Test
  public void testAssumption() {
    // lebih baik menggunakan assumeTrue dibandingkan if else
    assumeTrue("DEV".equals(System.getenv("PROFILE")));

    // test code for DEV environment
  }

}
