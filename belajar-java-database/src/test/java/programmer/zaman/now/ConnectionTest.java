package programmer.zaman.now;

import jdk.jshell.spi.ExecutionControl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

  @BeforeAll
  static void beforeAll() {
    try {
      Driver driver = new com.mysql.cj.jdbc.Driver();
      DriverManager.registerDriver(driver);
    } catch (SQLException exception) {
      Assertions.fail(exception);
    }
  }

  @Test
  void testConnection() {
    String jdbcUrl = "jdbc:mysql://localhost:3306/belajar_java_database";
    String username = "root";
    String password = "root123";

    try {
      Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
      System.out.println("Sukses connect ke database");
    } catch (SQLException exception) {
      Assertions.fail(exception);
    }
  }

  @Test
  void testConnectionClose() {
    String jdbcUrl = "jdbc:mysql://localhost:3306/belajar_java_database";
    String username = "root";
    String password = "root123";

    try {
      Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
      System.out.println("Sukses connect ke database");

      connection.close();
      System.out.println("Sukses close connection");
    } catch (SQLException exception) {
      Assertions.fail(exception);
    }
  }

  @Test
  void testConnectionCloseWithResource() {
    String jdbcUrl = "jdbc:mysql://localhost:3306/belajar_java_database";
    String username = "root";
    String password = "root123";

    try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
      System.out.println("Sukses connect ke database");
    } catch (SQLException exception) {
      Assertions.fail(exception);
    }
  }

}
