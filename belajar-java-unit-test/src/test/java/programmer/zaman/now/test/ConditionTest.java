package programmer.zaman.now.test;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import java.util.Properties;

public class ConditionTest {

  @Test
  @Disabled
  void testSystemProperties() {
    Properties properties = System.getProperties();
    properties.forEach((key, value) -> System.out.println(key + " : " + value));
  }

  @Test
  @EnabledOnOs({OS.WINDOWS})
  public void testRunWindows() {
    System.out.println("Run on Windows");
  }

  @Test
  @DisabledOnOs({OS.WINDOWS})
  public void testDisableWindows() {
    System.out.println("Disable on Windows");
  }

  @Test
  @EnabledOnJre({JRE.JAVA_17})
  void testEnabledOnjre() {
  }

  @Test
  @DisabledOnJre({JRE.JAVA_16})
  void testDisabledOnJre() {
  }

  @Test
  @EnabledForJreRange(min = JRE.JAVA_11, max = JRE.JAVA_17)
  void testEnabledForJreRange() {
  }

  @Test
  @DisabledForJreRange(min = JRE.JAVA_11, max = JRE.JAVA_17)
  void testDisabledForJreRange() {
  }

  @Test
  @EnabledIfSystemProperty(named = "java.vendor", matches = "Oracle Corporation")
  void enabledOnOracle() {
  }

  @Test
  @DisabledIfSystemProperty(named = "java.vendor", matches = "Oracle Corporation")
  void disabledOnOracle() {
  }

  @Test
  @EnabledIfSystemProperties({
      @EnabledIfSystemProperty(named = "java.vendor", matches = "Azul Systems, Inc."),
  })
  void testEnabledOnAzulInc() {
  }

  @Test
  @DisabledIfSystemProperties({
      @DisabledIfSystemProperty(named = "java.vendor", matches = "Azul Systems, Inc."),
  })
  void testDisabledOnAzulInc() {
  }

  @Test
  @EnabledIfSystemProperties({
      @EnabledIfSystemProperty(named = "PROFILE", matches = "DEV"),
  })
  void testEnabledOnProfileDev() {
  }

  @Test
  @DisabledIfSystemProperties({
      @DisabledIfSystemProperty(named = "PROFILE", matches = "DEV"),
  })
  void testDisabledOnProfileDev() {
  }
}
