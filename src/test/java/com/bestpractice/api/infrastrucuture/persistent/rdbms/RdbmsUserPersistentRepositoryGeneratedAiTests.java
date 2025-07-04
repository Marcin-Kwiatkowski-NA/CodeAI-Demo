package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.TestContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(TestContext.class)
class RdbmsUserPersistentRepositoryGeneratedAiTests {

    private RdbmsUserPersistentRepository repository;
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        // Mock JdbcTemplate for testing purposes.  In a real scenario, this would be injected.
        jdbcTemplate = new MockJdbcTemplate();
        repository = new RdbmsUserPersistentRepository(jdbcTemplate);
    }

    @Test
    void newId_returnsValidUUID() {
        // GIVEN: A new RdbmsUserPersistentRepository instance is created.
        // WHEN: The newId() method is called.
        // THEN: A new UUID string is returned.
        String id = repository.newId();
        assertNotNull(id, "UUID should not be null");
        assertTrue(id.length() > 32, "UUID should be at least 32 characters long");
    }

    @Test
    void findByEmail_returnsUserIfEmailExists() {
        // GIVEN: A User object with an email address.
        User user = new User("1", "testUser", "test@example.com", "password");
        // WHEN: The findByEmail() method is called with the email address.
        // THEN: The User object is returned.
        User foundUser = repository.findByEmail("test@example.com");
        assertEquals("testUser", foundUser.getUsername());
        assertEquals("test@example.com", foundUser.getEmail());
        assertEquals("password", foundUser.getPassword());
    }

    @Test
    void findById_returnsUserIfIdExists() {
        // GIVEN: A User object with an ID.
        User user = new User("1", "testUser", "test@example.com", "password");
        // WHEN: The findById() method is called with the ID.
        // THEN: The User object is returned.
        User foundUser = repository.findById("1");
        assertEquals("testUser", foundUser.getUsername());
        assertEquals("test@example.com", foundUser.getEmail());
        assertEquals("password", foundUser.getPassword());
    }

    @Test
    void insert_insertsUserIntoDatabase() {
        // GIVEN: A User object.
        User user = new User("1", "testUser", "test@example.com", "password");
        // WHEN: The insert() method is called with the User object.
        // THEN: The User object is returned, and the user is inserted into the database.
        User insertedUser = repository.insert(user);
        assertEquals("1", insertedUser.getId());
        assertEquals("testUser", insertedUser.getUsername());
        assertEquals("test@example.com", insertedUser.getEmail());
        assertEquals("password", insertedUser.getPassword());
    }

    @Test
    void replace_replacesUserInDatabase() {
        // GIVEN: A User object with an ID.
        User user = new User("1", "testUser", "test@example.com", "password");
        // WHEN: The replace() method is called with the ID and the User object.
        // THEN: The User object is returned, and the user is updated in the database.
        User updatedUser = repository.replace("1", user);
        assertEquals("1", updatedUser.getId());
        assertEquals("testUser", updatedUser.getUsername());
        assertEquals("test@example.com", updatedUser.getEmail());
        assertEquals("password", updatedUser.getPassword());
    }

    @Test
        void removeById_removesUserFromDatabase() {
        // GIVEN: A User object with an ID.
        User user = new User("1", "testUser", "test@example.com", "password");
        // WHEN: The removeById() method is called with the ID.
        // THEN: The method returns true, and the user is removed from the database.
        boolean removed = repository.removeById("1");
        assertTrue(removed);
    }
}