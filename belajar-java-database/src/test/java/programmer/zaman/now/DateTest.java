package programmer.zaman.now;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class DateTest {

  @Test
  @Disabled // Comment this line to enable the test
  void testDate() {
    String sql = """
        INSERT INTO sample_time(sample_time, sample_date, sample_timestamp)
        VALUES(?, ?, ?)
        """;

    try (Connection connection = ConnectionUtil.getDataSource().getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(sql)
    ) {
      preparedStatement.setTime(1, new Time(System.currentTimeMillis()));
      preparedStatement.setDate(2, new Date(System.currentTimeMillis()));
      preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      Assertions.fail(e);
    }
  }

  @Test
//  @Disabled // Comment this line to enable the test
  void testDateQuery() {
    String sql = """
        SELECT * FROM sample_time;
        """;

    try (Connection connection = ConnectionUtil.getDataSource().getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(sql)
    ) {
      ResultSet resultSet = preparedStatement.executeQuery();

      while(resultSet.next()) {
        Time time = resultSet.getTime("sample_time");
        Date date = resultSet.getDate("sample_date");
        Timestamp timestamp = resultSet.getTimestamp("sample_timestamp");

        System.out.println(String.join(" | ", time.toString(), date.toString(), timestamp.toString()));
      }
      resultSet.close();
    } catch (SQLException e) {
      Assertions.fail(e);
    }
  }
}
