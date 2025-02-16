package programmer.zaman.now;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class ConnectionPoolTest {

  @Test
  void testHikariCP() {
    HikariConfig config = new HikariConfig();
    config.setDriverClassName("com.mysql.cj.jdbc.Driver");
    config.setJdbcUrl("jdbc:mysql://localhost:3306/belajar_java_database");
    config.setUsername("root");
    config.setPassword("root123");

    config.setMaximumPoolSize(10);
    config.setMinimumIdle(5);
    config.setIdleTimeout(60_000);
    config.setMaxLifetime(10 * 60_000);

    try {
      HikariDataSource dataSource = new HikariDataSource(config);
      Connection connection = dataSource.getConnection();
      connection.close();
      dataSource.close();
    } catch (Exception exception) {
      Assertions.fail(exception);
    }
  }

  @Test
  void testUtil() {
    try {
      Connection connection = ConnectionUtil.getDataSource().getConnection();
      connection.close();
    } catch (Exception exception) {
      Assertions.fail(exception);
    }
  }
}
