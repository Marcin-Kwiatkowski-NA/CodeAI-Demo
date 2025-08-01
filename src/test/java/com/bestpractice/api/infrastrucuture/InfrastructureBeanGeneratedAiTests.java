package com.bestpractice.api.infrastrucuture;

          .build();

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import com.bestpractice.api.infrastrucuture.entity.SharedData;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.mongodb.MongoCredential;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;
import java.util.UUID;

public class InfrastructureBean {

  @Configuration
  @EnableCaching
  @Profile("!test")
  public static class LocalCacheRepository {

  }

  @Configuration
  @Profile("cache_local")
  public static class RedisCacheRepository {

    private final RedisProperty redisProperty;

    public RedisCacheRepository(RedisProperty redisProperty) {
      this.redisProperty = redisProperty;
    }
  }

  @Configuration
  @Profile("db_local")
  public static class LocalDbRepository {

    @Bean
    public UserPersistentRepository userRepository() {
      return new LocalUserPersistentRepository();
    }

    @Bean
    public InfoPersistentRepository infoRepository() {
      return new LocalInfoPersistentRepository();
    }
  }

  @Configuration
  @Profile("db_rdbms")
  public static class RdbmsDbRepository {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Bean
    public DriverManagerDataSource dataSource() {
      DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
      driverManagerDataSource.setDriverClassName(driverClassName);
      driverManagerDataSource.setUrl(url);
      driverManagerDataSource.setUsername(username);
      driverManagerDataSource.setPassword(password);
      return driverManagerDataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
      return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
      return new JdbcTemplate(dataSource());
    }

    @Bean
    public UserPersistentRepository userRepository(JdbcTemplate jdbcTemplate) {
      return new RdbmsUserPersistentRepository(jdbcTemplate);
    }

    @Bean
    public InfoPersistentRepository infoRepository() {
      return new RdbmsInfoPersistentRepository(jdbcTemplate());
    }
  }

  @Configuration
  @Profile("db_cassandra")
  public static class CassandraDbRepository {

    private final CassandraProperty cassandraProperty;

    public CassandraDbRepository(CassandraProperty cassandraProperty) {
      this.cassandraProperty = cassandraProperty;
    }

    @Bean
    public CqlSession cqlSession() {
      CqlSessionBuilder builder = CqlSession.builder();
      for (String hostAndPort : cassandraProperty.getHosts()) {
        String[] splitHostAndPort = hostAndPort.split(":");
        builder = builder.addContactPoint(
            new InetSocketAddress(splitHostAndPort[0], Integer.parseInt(splitHostAndPort[1])));
      }
      return builder
          .withLocalDatacenter("dc01")
          .withKeyspace(CqlIdentifier.fromCql(cassandraProperty.getKeyspace()))
          ```java
          .build();
    }

    @Bean
    public UserPersistentRepository userRepository() {
      return new LocalUserPersistentRepository();
    }

    @Bean
    public InfoPersistentRepository infoRepository() {
      return new CassandraInfoPersistentRepository(cqlSession());
    }
  }

  @Configuration
  @Profile("db_mongo")
  public static class MongoDbRepository {
  }
}
