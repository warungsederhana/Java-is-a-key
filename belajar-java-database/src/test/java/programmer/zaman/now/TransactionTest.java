package programmer.zaman.now;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionTest {

  @Test
  @Disabled // comment this to run testCommit
  void testCommit() {
    try (Connection connection = ConnectionUtil.getDataSource().getConnection()) {
      connection.setAutoCommit(false); // disable auto commit

      String sql = """
          INSERT INTO comments(email, comment) VALUES(?, ?);
          """;

      for (int i = 0; i < 100; i++) {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "eko@test.com");
        preparedStatement.setString(2, "hi");
        preparedStatement.executeUpdate();
        preparedStatement.close();
      }

      connection.commit(); // commit the transaction
    } catch (SQLException e) {
      Assertions.fail(e);
    }
  }

  @Test
  void testRollback() {
    try (Connection connection = ConnectionUtil.getDataSource().getConnection()) {
      connection.setAutoCommit(false); // disable auto commit

      String sql = """
          INSERT INTO comments(email, comment) VALUES(?, ?);
          """;

      for (int i = 0; i < 100; i++) {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "eko@test.com");
        preparedStatement.setString(2, "hi");
        preparedStatement.executeUpdate();
        preparedStatement.close();
      }

      connection.rollback(); // commit the transaction
    } catch (SQLException e) {
      Assertions.fail(e);
    }
  }
}
