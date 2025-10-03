package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@org.junit.jupiter.api.TestClassesContainer
class RdbmsUserPersistentRepositoryGeneratedAiTests {

    private RdbmsUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new RdbmsUserPersistentRepository(newJdbcTemplate());
    }

    @Test
    void newId() {
        // GIVEN: A new UserPersistentRepository instance is created.
        // WHEN: The newId() method is called.
        // THEN: A new UUID string is returned.
        String id = repository.newId();
        assertNotNull(id);
        assert(!id.isEmpty());
    }

    @Test
    void findByEmail() {
        // GIVEN: A User object with an email address.
        User user = new User("user1", "john", "john@example.com", "password");
        // WHEN: The findByEmail() method is called with the email address.
        // THEN: The User object is returned.
        User foundUser = repository.findByEmail("john@example.com");
        assertNotNull(foundUser);
        assertEquals("john", foundUser.getUsername());
        assertEquals("john@example.com", foundUser.getEmail());
    }

    @Test
    void findById() {
        // GIVEN: A User object with an ID.
        User user = new User("user1", "john", "john@example.com", "password");
        // WHEN: The findById() method is called with the ID.
        // THEN: The User object is returned.
        User foundUser = repository.findById("user1");
        assertNotNull(foundUser);
        assertEquals("john", foundUser.getUsername());
        assertEquals("john@example.com", foundUser.getEmail());
    }

    @Test
    void insert() {
        // GIVEN: A new User object.
        User user = new User("user1", "john", "john@example.com", "password");
        // WHEN: The insert() method is called.
        // THEN: The User object is returned, and the User object is inserted into the database.
        User insertedUser = repository.insert(user);
        assertNotNull(insertedUser);
        assertEquals("user1", insertedUser.getId());
        assertEquals("john", insertedUser.getUsername());
        assertEquals("john@example.com", insertedUser.getEmail());
    }

    @Test
    void replace() {
        // GIVEN: A User object with an ID.
        User user = new User("user1", "john", "john@example.com", "password");
        // WHEN: The replace() method is called with the ID and a modified User object.
        // THEN: The User object is updated in the database.
        User updatedUser = repository.replace("user1", new User("user1", "jane", "jane@example.com", "newpassword"));
        assertNotNull(updatedUser);
        assertEquals("jane", updatedUser.getUsername());
        assertEquals("jane@example.com", updatedUser.getEmail());
    }

    @Test
    void removeById() {
        // GIVEN: A User object with an ID.
        User user = new User("user1", "john", "john@example.com", "password");
        // WHEN: The removeById() method is called with the ID.
        // THEN: The User object is removed from the database.
        boolean removed = repository.removeById("user1");
        assertTrue(removed);
    }
}
