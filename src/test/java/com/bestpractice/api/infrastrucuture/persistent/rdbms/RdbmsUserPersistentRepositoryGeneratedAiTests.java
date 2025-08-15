package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RdbmsUserPersistentRepositoryGeneratedAiTests {

    private RdbmsUserPersistentRepository repository;
    private JdbcTemplate jdbcTemplate;
    private User user;

    @BeforeEach
    void setUp() {
        jdbcTemplate = new JdbcTemplate();
        repository = new RdbmsUserPersistentRepository(jdbcTemplate);
        user = new User("1", "testUser", "test@example.com", "password");
    }

    @AfterEach
    void tearDown() {
        String sql = "DELETE FROM users WHERE id = '1'";
        jdbcTemplate.update(sql);
    }

    @Test
    void newId_returnsValidUUID() {
        String id = repository.newId();
        assertNotNull(id);
        assertTrue(id.length() > 32);
    }

    @Test
    void findByEmail_returnsUser() {
        // Arrange
        User user = new User("1", "testUser", "test@example.com", "password");
        // Act
        User foundUser = repository.findByEmail(user.getEmail());
        // Assert
        assertEquals(user, foundUser);
    }

    @Test
    void findById_returnsUser() {
        // Arrange
        User user = new User("1", "testUser", "test@example.com", "password");
        // Act
        User foundUser = repository.findById(user.getId());
        // Assert
        assertEquals(user, foundUser);
    }

    @Test
    void insert_insertsUser() {
        // Arrange
        User user = new User("1", "testUser", "test@example.com", "password");
        // Act
        User insertedUser = repository.insert(user);
        // Assert
        assertEquals(user, insertedUser);
    }

    @Test
    void replace_replacesUser() {
        // Arrange
        User user = new User("1", "testUser", "test@example.com", "password");
        // Act
        User replacedUser = repository.replace("1", user);
        // Assert
        assertEquals(user, replacedUser);
    }

    @Test
    void removeById_removesUser() {
        // Arrange
        User user = new User("1", "testUser", "test@example.com", "password");
        // Act
        boolean removed = repository.removeById("1");
        // Assert
        assertTrue(removed);
    }
}
