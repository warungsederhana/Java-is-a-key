package programmer.zaman.now;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutoIncrementTest {

  @Test
  @Disabled // comment this to run testAutoIncrement
  void testAutoIncrement() {
    String sql = """
        INSERT INTO comments(email, comment) VALUES(?, ?);
        """;

    try (Connection connection = ConnectionUtil.getDataSource().getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
    ) {
      preparedStatement.setString(1, "eko@test.com");
      preparedStatement.setString(2, "hi");
      preparedStatement.executeUpdate();

      ResultSet resultSet = preparedStatement.getGeneratedKeys();

      if (resultSet.next()) {
        System.out.println("ID: " + resultSet.getInt(1));
      }
    } catch (SQLException e) {
      Assertions.fail(e);
    }
  }
}
