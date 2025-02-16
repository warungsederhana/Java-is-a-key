package programmer.zaman.now;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverTest {

  @Test
  void testRegister() {
    try {
      // Polymorphism Driver java.sql
      // Driver com.mysql.cj.jdbc.Driver
      Driver mysqlDriver = new com.mysql.cj.jdbc.Driver();
      DriverManager.registerDriver(mysqlDriver);
    } catch( SQLException exception) {
      Assertions.fail(exception);
    }
  }
}
