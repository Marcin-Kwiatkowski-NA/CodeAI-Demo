package com.bestpractice.api.infrastrucuture.persistent.local;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.MockitoExtension;
import org.junit.jupiter.api.extension.MockitoJUnitRunner;
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
        user = new User("user123", "john.doe", "john.doe@example.com", "password123");
    }

    @Test
    void newId_returnsValidUUID() {
        // GIVEN: A new UUID should be generated.
        // WHEN: The newId() method is called.
        // THEN: A valid UUID string is returned.
        String uuid = repository.newId();
        assertNotNull(uuid, "UUID cannot be null");
        assertTrue(uuid.length() > 32, "UUID should be at least 32 characters long");
    }

    @Test
    void findByEmail_returnsUserIfFound() {
        // GIVEN: A user with the specified email exists in the repository.
        repository.insert(user);
        // WHEN: The findByEmail() method is called with the user's email.
        // THEN: The user object is returned.
        User foundUser = repository.findByEmail(user.getEmail());
        assertNotNull(foundUser, "User cannot be null");
        assertEquals(user.getUsername(), foundUser.getUsername(), "Username should match");
        assertEquals(user.getEmail(), foundUser.getEmail(), "Email should match");
    }

    @Test
    void findByEmail_returnsNullIfUserNotFound() {
        // GIVEN: No user exists in the repository with the specified email.
        // WHEN: The findByEmail() method is called with a non-existent email.
        // THEN: Null is returned.
        User nullUser = repository.findByEmail("nonexistent@example.com");
        assertNull(nullUser, "User should be null when not found");
    }

    @Test
    void findById_returnsUserIfFound() {
        // GIVEN: A user exists in the repository with the specified ID.
        repository.insert(user);
        // WHEN: The findById() method is called with the user's ID.
        // THEN: The user object is returned.
        User foundUser = repository.findById(user.getId());
        assertNotNull(foundUser, "User cannot be null");
        assertEquals(user.getUsername(), foundUser.getUsername(), "Username should match");
        assertEquals(user.getEmail(), foundUser.getEmail(), "Email should match");
    }

    @Test
    void findById_returnsNullIfUserNotFound() {
        // GIVEN: No user exists in the repository with the specified ID.
        // WHEN: The findById() method is called with a non-existent ID.
        // THEN: Null is returned.
        User nullUser = repository.findById("nonexistentid");
        assertNull(nullUser, "User should be null when not found");
    }

    @Test
    void insert_returnsInsertedUser() {
        // GIVEN: A new user object is created.
        // WHEN: The insert() method is called with the user object.
        // THEN: The user object is returned, and it is added to the repository.
        User insertedUser = repository.insert(user);
        assertEquals(user.getId(), insertedUser.getId(), "ID should match");
        assertEquals(user.getUsername(), insertedUser.getUsername(),java
        assertEquals(user.getEmail(), insertedUser.getEmail(), "Email should match");
    }

    @Test
    void replace_returnsNullIfReplacementSuccessful() {
        // GIVEN: A user exists in the repository with the specified ID.
        repository.insert(user);
        // WHEN: The replace() method is called with the user's ID and a new user object.
        // THEN: Null is returned, and the user object in the repository is updated.
        User replacedUser = repository.replace(user.getId(), new User("newid", "newuser", "newemail", "newpassword"));
        assertNull(replacedUser, "Replaced user should be null");
        assertEquals("newid", replacedUser.getUsername(), "Username should be updated");
        assertEquals("newemail", replacedUser.getEmail(), "Email should be updated");
    }

    @Test
en    void replace_throwsExceptionIfUserNotFound() {
        // GIVEN: No user exists in the repository with the specified ID.
        // WHEN: The replace() method is called with the user's ID and a new user object.
        // THEN: A RuntimeException is thrown with the message "Data does not exist."
        assertThrows(RuntimeException.class, () -> repository.replace("nonexistentid", new User("newid", "newuser", "newemail", "newpassword")));
    }

    @Test
    void removeById_returnsTrueIfRemovalSuccessful() {
        // GIVEN: A user exists in the repository with the specified ID.
        repository.insert(user);
        // WHEN: The removeById() method is called with the user's ID.
        // THEN: True is returned, and the user object is removed from the repository.
        boolean removed = repository.removeById(user.getId());
        assertTrue(removed, "Removal should be successful");
        assertNull(repository.findById(user.getId()), "User should be null after removal");
    }

    @Test
    void removeById_returnsTrueEvenIfUserNotFound() {
        // GIVEN: No user exists in the repository with the specified ID.
        // WHEN: The removeById() method is called with a non-existent ID.
        // THEN: True is returned, which is the expected behavior.
        boolean removed = repository.removeById("nonexistentid");
        assertTrue(removed, "Removal should be successful");
    }
}