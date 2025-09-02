package com.bestpractice.api.infrastrucuture.persistent.local;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@MockitoJUnitRunner
public class LocalUserPersistentRepositoryGeneratedAiTests {

    private LocalUserPersistentRepository repository;
    private User user;

    @BeforeEach
    void setUp() {
        repository = new LocalUserPersistentRepository();
        user = new User("1", "testUser", "test@example.com", "password");
    }

    @Test
    void newId_returnsValidUUID() {
        // GIVEN: A new UUID should be generated.
        // WHEN: The newId() method is called.
        // THEN: A valid UUID string is returned.
        String generatedId = repository.newId();
        assertNotNull(generatedId, "UUID should not be null");
        assertTrue(generatedId.length() > 20, "UUID should be long enough");
    }

    @Test
    void findByEmail_returnsUserWhenFound() {
        // GIVEN: A user with the specified email exists in the repository.
        repository.insert(user);
        // WHEN: The findByEmail() method is called with the user's email.
        // THEN: The user object is returned.
        User foundUser = repository.findByEmail(user.getEmail());
        assertNotNull(foundUser, "User should not be null");
        assertEquals(user.getId(), foundUser.getId(), "User IDs should match");
        assertEquals(user.getUsername(), foundUser.getUsername(), "User names should match");
        assertEquals(user.getEmail(), foundUser.getEmail(), "User emails should match");
        assertEquals(user.getPassword(), foundUser.getPassword(), "User passwords should match");
    }

    @Test
    void findByEmail_returnsNullWhenNotFound() {
        // GIVEN: No user exists in the repository with the specified email.
        // WHEN: The findByEmail() method is called with the email.
        // THEN: Null is returned.
        User notFoundUser = repository.findByEmail("nonexistent@example.com");
        assertNull(notFoundUser, "User should be null when not found");
    }

    @Test
    void findById_returnsUserById() {
        // GIVEN: A user exists in the repository.
        repository.insert(user);
        // WHEN: The findById() method is called with the user's ID.
        // THEN: The user object is returned.
        User foundUser = repository.findById(user.getId());
        assertNotNull(foundUser, "User should not be null");
        assertEquals(user.getId(), foundUser.getId(), "User IDs should match");
        assertEquals(user.getUsername(), foundUser.getUsername(), "User names should match");
        assertEquals(user.getEmail(), foundUser.getEmail(), "User emails should match");
        assertEquals(user.getPassword(), foundUser.getPassword(), "User passwords should match");
    }

    @Test
    void findById_returnsNullWhenNotFound() {
        // GIVEN: No user exists in the repository with the specified ID.
        // WHEN: The findById() method is called with the ID.
        // THEN: Null is returned.
        User notFoundUser = repository.findById("nonexistentId");
        assertNull(notFoundUser, "User should be null when not found");
    }

    @Test
    void insert_returnsInsertedUser() {
        // GIVEN: A new user object.
        // WHEN: The insert() method is called with the user object.
        // THEN: The user object is returned, and it is added to the repository.
        User insertedUser = repository.insert(user);
        assertEquals(user.getId(), insertedUser.getId(), "User IDs should match");
        assertEquals(user.getUsername(), insertedUser.getUsername(), "User names should match");
        assertEquals(userjava
        assertEquals(user.getEmail(), insertedUser.getEmail(), "User emails should match");
        assertEquals(user.getPassword(), insertedUser.getPassword(), "User passwords should match");
    }

    @Test
    void replace_returnsUpdatedUser() {
        // GIVEN: A user exists in the repository.
        repository.insert(user);
        // WHEN: The replace() method is called with the user's ID and a modified user object.
        User updatedUser = new User(user.getId(), "newUsername", "newEmail", "newPassword");
        User replacedUser = repository.replace(user.getId(), updatedUser);
        assertNotNull(replacedUser, "Replaced user should not be null");
        assertEquals("newUsername", replacedUser.getUsername(), "User names should match");
        assertEquals("newEmail", replacedUser.getEmail(), "User emails should match");
        assertEquals("newPassword", replacedUser.getPassword(), "User passwords should match");
    }

    @Test
    void replace_returnsNullWhenNotFound() {
        // GIVEN: No user exists in the repository with the specified ID.
        // WHEN: The replace() method is called with the ID and a user object.
        // THEN: Null is returned.
        User notFoundUser = repository.replace("nonexistentId", user);
        assertNull(notFoundUser, "Replaced user should be null when not found");
    }

    @Test
    void removeById_returnsTrueWhenRemoved() {
        // GIVEN: A user exists in the repository.
        repository.insert(user);
        // WHEN: The removeById() method is called with the user's ID.
        // THEN: The user is removed from the repository, and true is returned.
        boolean removed = repository.removeById(user.getId());
        assertTrue(removed, "Removal should be successful");
        assertNull(repository.findById(user.getId()), "User should be null after removal");
    }

    @Test
    void removeById_returnsTrueWhenAlreadyRemoved() {
        // GIVEN: A user exists in the repository.
        repository.insert(user);
        // WHEN: The removeById() method is called with the user's ID.
        // THEN: The user is already removed from the repository, and true is returned.
        boolean alreadyRemoved = repository.removeById(user.getId());
        assertTrue(alreadyRemoved, "Removal should be successful");
    }

    @Test
    void removeById_returnsTrueWhenAlreadyRemoved() {
        // GIVEN: A user exists in the repository.
        repository.insert(user);
        // WHEN: The removeById() method is called with the user's ID.
        // THEN: The user is already removed from the repository, and true is returned.
        boolean alreadyRemoved = repository.removeById(user.getId());
        assertTrue(alreadyRemoved, "Removal should be successful");
    }
}