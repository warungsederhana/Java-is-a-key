package com.programmerzamannow.spring.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@ConfigurationProperties("application")
public class ApplicationProperties {
  private String name;
  private Integer version;
  private Boolean isProduction;
  private DatabaseProperties database;
  private List<Role> defaultRoles;
  private Map<String, Role> roles;
  private Duration defaultTimeout;
  private Date expiredDate;

  @Data
  public static class DatabaseProperties {
    private String database;
    private String username;
    private String password;
    private String url;
    private List<String> whitelistTables;
    private Map<String, Integer> maxTableSize;
  }

  @Data
  public static class Role {
    private String id;
    private String name;
  }
}
