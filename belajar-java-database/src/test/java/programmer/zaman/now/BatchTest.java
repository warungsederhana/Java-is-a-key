package programmer.zaman.now;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchTest {

  @Test
  @Disabled // comment this to run testStatement
  void testStatement() {
    try (Connection connection = ConnectionUtil.getDataSource().getConnection();
         Statement statement = connection.createStatement();
    ) {
      String sql = """
        INSERT INTO comments(email, comment) VALUES('eko@test.com', 'hi');
        """;

      for(int i = 0; i < 100; i++) {
        statement.addBatch(sql);
      }

      statement.executeBatch();
    } catch (SQLException e) {
      Assertions.fail(e);
    }
  }

  @Test
//  @Disabled // comment this to run testStatement
  void testPreparedStatement() {
    String sql = """
        INSERT INTO comments(email, comment) VALUES(?, ?);
        """;

    try (Connection connection = ConnectionUtil.getDataSource().getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(sql);
    ) {
      for(int i = 0; i < 100; i++) {
        preparedStatement.clearParameters();

        preparedStatement.setString(1, "eko@test.com");
        preparedStatement.setString(2, "hi");
        preparedStatement.addBatch();
      }

      preparedStatement.executeBatch();
    } catch (SQLException e) {
      Assertions.fail(e);
    }
  }
}
