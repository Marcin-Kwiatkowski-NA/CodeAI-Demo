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
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ExtendWith.class)
class RdbmsUserPersistentRepositoryGeneratedAiTests {

    private RdbmsUserPersistentRepository repository;
    private org.springframework.jdbc.core.JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        // Mocking the JdbcTemplate is not needed for this specific implementation.
        // We'll use a real JdbcTemplate for demonstration purposes.
        jdbcTemplate = new org.springframework.jdbc.core.JdbcTemplate();
        repository = new RdbmsUserPersistentRepository(jdbcTemplate);
    }

    @Test
    void newId_returnsValidUUID() {
        String id = repository.newId();
        assertNotNull(id, "Generated ID should not be null");
        assertTrue(id.matches("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}"), "Generated ID should be a valid UUID");
    }

    @Test
    void findByEmail_returnsUser() {
        // Arrange
        User user = new User("1", "testUser", "test@example.com", "password");
        // Act
        User foundUser = repository.findByEmail(user.getEmail());
        // Assert
        assertEquals(user, foundUser, "User should match the email");
    }

    @Test
    void findById_returnsUser() {
        // Arrange
        User user = new User("1", "testUser", "test@example.com", "password");
        // Act
        User foundUser = repository.findById(user.getId());
        // Assert
        assertEquals(user, foundUser, "User should match the ID");
    }

    @Test
    void insert_insertsUserAndReturnsUser() {
        // Arrange
        User user = new User("1", "testUser", "test@example.com", "password");
        // Act
        User insertedUser = repository.insert(user);
        // Assert
        assertEquals(user, insertedUser, "Inserted user should be the same as the original");
    }

    @Test
    void replace_replacesUserAndReturnsUser() {
        // Arrange
        User user = new User("1", "testUser", "test@example.com", "password");
        // Act
        User replacedUser = repository.replace(user.getId(), user);
        // Assert
        assertEquals(user, replacedUser, "Replaced user should be the same as the original");
    }

    @Test
    void removeById_removesUserAndReturnsTrue() {
        // Arrange
        User user = new User("1", "testUser", "test@example.com", "password");
        // Act
        boolean removed = repository.removeById(user.getId());
        // Assert
        assertTrue(removed, "User should be removed");
    }
}
