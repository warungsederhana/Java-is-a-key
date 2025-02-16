package programmer.zaman.now;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementTest {

  @Test
  void testPreparedStatement() {
    String sql = """
        SELECT * FROM admin WHERE username = ? AND password = ?
        """;

    try (Connection connection = ConnectionUtil.getDataSource().getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(sql);
    ) {
      preparedStatement.setString(1, "admin");
      preparedStatement.setString(2, "admin");

      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        System.out.println("Login Success: " + resultSet.getString("username"));
      } else {
        System.out.println("Login Failed");
      }

    } catch (SQLException e) {
      Assertions.fail(e);
    }
  }
}
