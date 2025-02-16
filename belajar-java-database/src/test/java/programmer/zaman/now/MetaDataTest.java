package programmer.zaman.now;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class MetaDataTest {

  @Test
  void testDatabaseMetaData() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    DatabaseMetaData databaseMetaData = connection.getMetaData();

    System.out.println(databaseMetaData.getDatabaseProductName());
    System.out.println(databaseMetaData.getDatabaseProductVersion());

    ResultSet tables = databaseMetaData.getTables("belajar_java_database", null, null, new String[]{"TABLE"});

    while (tables.next()) {
      String tableName = tables.getString("TABLE_NAME");
      System.out.println("Table Name: " + tableName);
    }

    connection.close();
  }

  @Test
  void testParameterMetaData() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();

    String sql = """
        INSERT INTO comments(email, comment) VALUES(?, ?);
        """;

    PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

    ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();

    System.out.println(parameterMetaData.getParameterCount());
//    System.out.println(parameterMetaData.getParameterTypeName(1));      // belum support

    preparedStatement.close();
    connection.close();
  }

  @Test
  void testResultSetMetaData() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    Statement statement = connection.createStatement();

    String sql = """
        SELECT * FROM comments;
        """;

    ResultSet resultSet = statement.executeQuery(sql);
    ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

    System.out.println(resultSetMetaData.getColumnCount());
    for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
      System.out.println(
          String.join(
              " | ",
              resultSetMetaData.getColumnName(i),
              Integer.toString(resultSetMetaData.getColumnType(i)),
              resultSetMetaData.getColumnTypeName(i)
          )
      );
    }

    resultSet.close();
    statement.close();
    connection.close();
  }
}
