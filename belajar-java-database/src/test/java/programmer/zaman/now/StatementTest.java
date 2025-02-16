package programmer.zaman.now;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementTest {

  @Test
  void testCreateStatement() {
    try {
      Connection connection = ConnectionUtil.getDataSource().getConnection();
      Statement statement = connection.createStatement();

      statement.close();
      connection.close();
    } catch (SQLException e) {
      Assertions.fail(e);
    }
  }

  @Test
  @Disabled  // komen ini untuk menjalankan testExecuteUpdate
  void testExecuteUpdate() {
    try {
      Connection connection = ConnectionUtil.getDataSource().getConnection();
      Statement statement = connection.createStatement();

      String sql = """
          INSERT INTO customers(id, name, email)
          VALUES('eko', 'eko', 'eko@test.com')
          """;

      int update = statement.executeUpdate(sql);
      System.out.println(update);

      statement.close();
      connection.close();
    } catch (SQLException e) {
      Assertions.fail(e);
    }
  }

  @Test
  @Disabled // komen ini untuk menjalankan testExecuteUpdate
  void testExecuteDelete() {
    try {
      Connection connection = ConnectionUtil.getDataSource().getConnection();
      Statement statement = connection.createStatement();

      String sql = """
          DELETE FROM customers;
          """;

      int update = statement.executeUpdate(sql);
      System.out.println(update);

      statement.close();
      connection.close();
    } catch (SQLException e) {
      Assertions.fail(e);
    }
  }

  @Test
//  @Disabled  // komen ini untuk menjalankan testExecuteQuery
  void testExecuteQuery() {
    try {
      Connection connection = ConnectionUtil.getDataSource().getConnection();
      Statement statement = connection.createStatement();

      String sql = """
          SELECT * FROM customers
          """;

      ResultSet resultSet = statement.executeQuery(sql);

      resultSet.close();
      statement.close();
      connection.close();
    } catch (SQLException e) {
      Assertions.fail(e);
    }
  }
}
