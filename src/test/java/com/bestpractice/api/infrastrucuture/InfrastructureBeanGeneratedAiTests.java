package com.bestpractice.api.infrastrucuture;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.local.LocalInfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.local.LocalUserPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import static org.junit.jupiter.api.Assertions.*;

public class InfrastructureBeanGeneratedAiTests {

    private InfrastructureBean.LocalDbRepository localDbRepository;
    private InfrastructureBean.RdbmsDbRepository rdbmsDbRepository;

    @BeforeEach
    void setUp() {
        localDbRepository = new InfrastructureBean.LocalDbRepository();
        rdbmsDbRepository = new TestRdbmsDbRepository("jdbc:h2:mem:testdb", "sa", "", "org.h2.Driver");
    }

    static class TestRdbmsDbRepository extends InfrastructureBean.RdbmsDbRepository {
        TestRdbmsDbRepository(String url, String username, String password, String driverClassName) {
            try {
                java.lang.reflect.Field urlField = InfrastructureBean.RdbmsDbRepository.class.getDeclaredField("url");
                urlField.setAccessible(true);
                urlField.set(this, url);

                java.lang.reflect.Field usernameField = InfrastructureBean.RdbmsDbRepository.class.getDeclaredField("username");
                usernameField.setAccessible(true);
                usernameField.set(this, username);

                java.lang.reflect.Field passwordField = InfrastructureBean.RdbmsDbRepository.class.getDeclaredField("password");
                passwordField.setAccessible(true);
                passwordField.set(this, password);

                java.lang.reflect.Field driverClassNameField = InfrastructureBean.RdbmsDbRepository.class.getDeclaredField("driverClassName");
                driverClassNameField.setAccessible(true);
                driverClassNameField.set(this, driverClassName);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    void givenLocalDbRepository_whenUserRepository_thenReturnsLocalUserPersistentRepository() {
        // GIVEN

        // WHEN
        UserPersistentRepository repo = localDbRepository.userRepository();

        // THEN
        assertNotNull(repo);
        assertTrue(repo instanceof LocalUserPersistentRepository);
    }

    @Test
    void givenLocalDbRepository_whenInfoRepository_thenReturnsLocalInfoPersistentRepository() {
        // GIVEN

        // WHEN
        InfoPersistentRepository repo = localDbRepository.infoRepository();

        // THEN
        assertNotNull(repo);
        assertTrue(repo instanceof LocalInfoPersistentRepository);
    }

    @Test
    void givenRdbmsDbRepository_whenDataSource_thenReturnsValidDataSource() {
        // GIVEN

        // WHEN
        DriverManagerDataSource ds = rdbmsDbRepository.dataSource();

        // THEN
        assertNotNull(ds);
        assertEquals("jdbc:h2:mem:testdb", ds.getUrl());
    }

    @Test
    void givenRdbmsDbRepository_whenJdbcTemplate_thenReturnsJdbcTemplate() {
        // GIVEN

        // WHEN
        JdbcTemplate jdbcTemplate = rdbmsDbRepository.jdbcTemplate();

        // THEN
        assertNotNull(jdbcTemplate);
        assertNotNull(jdbcTemplate.getDataSource());
    }

    @Test
    void givenRdbmsDbRepository_whenUserRepository_thenReturnsRdbmsUserPersistentRepository() {
        // GIVEN
        JdbcTemplate jdbcTemplate = rdbmsDbRepository.jdbcTemplate();

        // WHEN
        UserPersistentRepository repo = rdbmsDbRepository.userRepository(jdbcTemplate);

        // THEN
        assertNotNull(repo);
        assertEquals("com.bestpractice.api.infrastrucuture.persistent.rdbms.RdbmsUserPersistentRepository", repo.getClass().getName());
    }

    @Test
    void givenRdbmsDbRepository_whenInfoRepository_thenReturnsRdbmsInfoPersistentRepository() {
        // GIVEN

        // WHEN
        InfoPersistentRepository repo = rdbmsDbRepository.infoRepository();

        // THEN
        assertNotNull(repo);
        assertEquals("com.bestpractice.api.infrastrucuture.persistent.rdbms.RdbmsInfoPersistentRepository", repo.getClass().getName());
    }
}
