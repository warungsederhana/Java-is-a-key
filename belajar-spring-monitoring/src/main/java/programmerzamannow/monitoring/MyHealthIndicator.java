package programmerzamannow.monitoring;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Component
public class MyHealthIndicator extends AbstractHealthIndicator {
  // This method is called to perform custom health checks
  // in actuator endpoints its called my

  @Override
  protected void doHealthCheck(Health.Builder builder) throws Exception {
    builder.status(Status.UP);
//    builder.status(Status.DOWN);
    builder.withDetail("app", "OK");
    builder.withDetail("error", "NO ERROR");
  }
}
