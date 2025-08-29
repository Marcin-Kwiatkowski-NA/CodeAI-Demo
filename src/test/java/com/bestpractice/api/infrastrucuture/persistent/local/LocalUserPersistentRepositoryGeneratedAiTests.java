package com.bestpractice.api.infrastrucuture.persistent.local;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
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
        assertTrue(uuid.length() > 32, "UUID length should be greater than 32");
    }

    @Test
    void findByEmail_returnsUserWhenFound() {
        // GIVEN: A user with the specified email exists in the repository.
        repository.insert(user);
        // WHEN: The findByEmail("john.doe@example.com") method is called.
        // THEN: The user object with the matching email is returned.
        User foundUser = repository.findByEmail("john.doe@example.com");
        assertNotNull(foundUser, "User cannot be null");
        assertEquals("john.doe", foundUser.getUsername());
        assertEquals("john.doe", foundUser.getEmail());
    }

    @Test
    void findByEmail_returnsNullWhenNotFound() {
        // GIVEN: No user exists in the repository with the specified email.
        // WHEN: The findByEmail("nonexistent@example.com") method is called.
        // THEN: Null is returned.
        User notFoundUser = repository.findByEmail("nonexistent@example.com");
        assertNull(notFoundUser, "User should be null when not found");
    }

    @Test
    void findById_returnsUserById() {
        // GIVEN: A user exists in the repository with a specific ID.
        repository.insert(user);
        // WHEN: The findById("user123") method is called.
        // THEN: The user object with the matching ID is returned.
        User foundUser = repository.findById("user123");
        assertNotNull(foundUser, "User cannot be null");
        assertEquals("john.doe", foundUser.getUsername());
        assertEquals("john.doe", foundUser.getEmail());
    }

    @Test
    void insert_returnsNewUser() {
        // GIVEN: A new user object is created.
        // WHEN: The insert(user) method is called.
        // THEN: The new user object is added to the repository, and the same user object is returned.
        User insertedUser = repository.insert(user);
        assertNotNull(insertedUser, "User cannot be null after insertion");
        assertEquals("john.doe", insertedUser.getUsername());
        assertEquals("john.doe", insertedUser.getEmail());
    }

    @Test
    void replace_returnsNullWhenSuccessful() {
        // GIVEN: A user exists in the repository with the specified ID.
        repository.insert(user);
        // WHEN: The replace("user123", new User("user456", "jane.doe", "jane.doe@example.com", "password789")) method is called.
        // THEN: Null is returned, and the user in the repository is updated withjava
        }
    }

    @Test
    void replace_throwsExceptionWhenNotFound() {
        // GIVEN: No user exists in the repository with the specified ID.
        // WHEN: The replace("nonexistent", new User("user456", "jane.doe", "jane.doe@example.com", "password789")) method is called.
        // THEN: A RuntimeException is thrown with the message "Data does not exist.".
        try {
            repository.replace("nonexistent", new User("user456", "jane.doe", "jane.doe@example.com", "password789"));
            fail("Exception was not thrown");
        } catch (RuntimeException e) {
            assertEquals("Data does not exist.", e.getMessage());
        }
    }

    @Test
    void removeById_returnsTrueWhenSuccessful() {
        // GIVEN: A user exists in the repository with the specified ID.
        repository.insert(user);
        // WHEN: The removeById("user123") method is called.
        // THEN: The user object with the matching ID is removed from the repository, and true is returned.
        assertTrue(repository.removeById("user123"));
        assertFalse(repository.users.contains(user));
    }

    @Test
    void removeById_returnsTrueWhenNotFound() {
        // GIVEN: No user exists in the repository with the specified ID.
        // WHEN: The removeById("nonexistent") method is called.
        // THEN: True is returned, and the repository remains unchanged.
        assertTrue(repository.removeById("nonexistent"));
    }
}
