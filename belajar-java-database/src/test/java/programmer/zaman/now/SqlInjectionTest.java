package programmer.zaman.now;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlInjectionTest {

  @Test
//  @Disabled  // komen ini untuk menjalankan testSqlInjection
  void testSqlInjection() {
    try {
      Connection connection = ConnectionUtil.getDataSource().getConnection();
      Statement statement = connection.createStatement();

      String username = "admin'; #";
      String password = "salah";

      String sql = "SELECT * FROM admin WHERE username = '" + username + "' AND password = '" + password + "'";

      ResultSet resultSet = statement.executeQuery(sql);

      if (resultSet.next()) {
        System.out.println("Login Success: " + resultSet.getString("username"));
      } else {
        System.out.println("Login Failed");
      }

      resultSet.close();
      statement.close();
      connection.close();
    } catch (SQLException e) {
      Assertions.fail(e);
    }
  }
}
