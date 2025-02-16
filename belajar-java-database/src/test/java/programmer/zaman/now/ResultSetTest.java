package programmer.zaman.now;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetTest {
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

      while(resultSet.next()) {
//        jika query join table, maka harus disesuaikan dengan nama kolom yang diambil
//        String id = resultSet.getString("customers.id");
        String id = resultSet.getString("id");
        String name = resultSet.getString("name");
        String email = resultSet.getString("email");

        System.out.println(String.join(", ", id, name, email));
      }

      resultSet.close();
      statement.close();
      connection.close();
    } catch (SQLException e) {
      Assertions.fail(e);
    }
  }
}
