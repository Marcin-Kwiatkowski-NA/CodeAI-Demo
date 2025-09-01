package com.bestpractice.api.infrastrucuture;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.*;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntity;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import java.util.List;
import org.bson.codecs.configuration.CodecRegistry;

@Configuration
@EnableCaching
@Profile("!test")
public class InfrastructureBean {

  @Configuration
  @Profile("cache_local")
  public static class LocalCacheRepository {

  }

  @Configuration
  @Profile("cache_redis")
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
  public static class MongoDbRepository {java
  @Bean
  public InfoPersistentRepository infoRepository() {
      return new MongoInfoPersistentRepository(cqlSession(), mongoDatabase());
  }
}
