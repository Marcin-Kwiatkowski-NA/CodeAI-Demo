package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RdbmsUserPersistentRepositoryGeneratedAiTests {

    @Test
    void newId_returnsValidUUID() {
        // GIVEN: We want to test the newId method.
        // WHEN: We call the newId method.
        String id = repository.newId();
        // THEN: The returned value should be a valid UUID string.
        assertNotNull(id, "UUID should not be null");
        assertTrue(id.matches("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}"), "UUID should match the correct format");
    }

    @Test
    void findByEmail_returnsUserIfEmailExists() {
        // GIVEN: A user with a specific email address exists in the database.
        User user = new User("1", "testUser", "test@example.com", "password");
        // WHEN: We call the findByEmail method with the user's email address.
        User foundUser = repository.findByEmail("test@example.com");
        // THEN: The returned user object should be the same as the user object created.
        assertEquals("1", foundUser.getId(), "ID should match");
        assertEquals("testUser", foundUser.getUsername(), "Username should match");
        assertEquals("test@example.com", foundUser.getEmail(), "Email should match");
        assertEquals("password", foundUser.getPassword(), "Password should match");
    }

    @Test
    void findById_returnsUserIfIdExists() {
        // GIVEN: A user with a specific ID exists in the database.
        User user = new User("1", "testUser", "test@example.com", "password");
        // WHEN: We call the findById method with the user's ID.
        User foundUser = repository.findById("1");
        // THEN: The returned user object should be the same as the user object created.
        assertEquals("1", foundUser.getId(), "ID should match");
        assertEquals("testUser", foundUser.getUsername(), "Username should match");
        assertEquals("test@example.com", foundUser.getEmail(), "Email should match");
        assertEquals("password", foundUser.getPassword(), "Password should match");
    }

    @Test
    void insert_insertsUserAndReturnsUser() {
        // GIVEN: We have a user object to insert.
        User user = new User("1", "testUser", "test@example.com", "password");
        // WHEN: We call the insert method with the user object.
        User insertedUser = repository.insert(user);
        // THEN: The returned user object should be the same as the user object passed to the method.
        assertEquals("1", insertedUser.getId(), "ID should match");
        assertEquals("testUser", insertedUser.getUsername(), "Username should match");
        assertEquals("test@example.com", insertedUser.getEmail(), "Email should match");
        assertEquals("password", insertedUser.getPassword(), "Password should match");
    }

    @Test
    void replace_replacesUserAndReturnsUser() {
        // GIVEN: A user with a specific ID exists in the database.
        User user = new User("1", "testUser", "test@example.com", "password");
        // WHEN: We call the replace method with the user object.
        User replacedUser = repository.replace(user);
        // THEN: The returned user object should be the same as the user object passed to the method.
        assertEquals("1", replacedUser.getId(), "ID should match");
        assertEquals("testUser", replacedUser.getUsername(), "Username should match");
        assertEquals("test@example.com", replacedUser.getEmail(), "Email should match");
        assertEquals("password", replacedUser.getPassword(), "Password should match");
    }

    @Test
    void update_updatesUserAndReturnsUpdatedUser() {
        // GIVEN: A user with a specific ID exists in the database.
        User user = new User("1", "testUser", "test@example.com", "password");
        // WHEN: We call the update method with the user object.
        User updatedUser = repository.update(user);
        // THEN: The returned user object should be the same as the user object passed to thejava
        // GIVEN: A user with a specific ID exists in the database.
        // WHEN: We call the update method with the user object.
        User updatedUser = repository.update(user);
        // THEN: The returned user object should be the same as the user object passed to the method, but with the email address changed.
        assertEquals("1", updatedUser.getId(), "ID should match");
        assertEquals("testUser", updatedUser.getUsername(), "Username should match");
        assertEquals("newEmail@example.com", updatedUser.getEmail(), "Email should match");
        assertEquals("password", updatedUser.getPassword(), "Password should match");
    }
}
