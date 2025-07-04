package com.bestpractice.api.infrastrucuture.persistent.local;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class LocalUserPersistentRepositoryGeneratedAiTests {

    @ExtendWith(ExtensionContext.class)
    public static class LocalUserPersistentRepositoryTests {

        private final LocalUserPersistentRepository repository = new LocalUserPersistentRepository();
        private User user;

        @BeforeEach
        void setUp() {
            user = new User("user123", "testuser", "test@example.com", "password123");
            repository.users.add(user);
        }

        @Test
        void newId_returns_unique_uuid() {
            // GIVEN: A new UUID should be generated.
            // WHEN: The newId() method is called.
            // THEN: A new UUID string is returned.
            String id = repository.newId();
            assertNotNull(id);
            assertNotEquals(id, id);
        }

        @Test
        void findByEmail_returns_user_if_exists() {
            // GIVEN: A user with the specified email exists in the repository.
            // WHEN: The findByEmail() method is called with the user's email.
            // THEN: The user object is returned.
            User foundUser = repository.findByEmail("test@example.com");
            assertNotNull(foundUser);
            assertEquals("testuser", foundUser.getUsername());
            assertEquals("test@example.com", foundUser.getEmail());
        }

        @Test
        void findByEmail_returns_null_if_user_not_found() {
            // GIVEN: A user with the specified email does not exist in the repository.
            // WHEN: The findByEmail() method is called with the email.
            // THEN: Null is returned.
            assertNull(repository.findByEmail("nonexistent@example.com"));
        }

        @Test
        void findById_returns_user_if_exists() {
            // GIVEN: A user with the specified ID exists in the repository.
            // WHEN: The findById() method is called with the user's ID.
            // THEN: The user object is returned.
            User foundUser = repository.findById("user123");
            assertNotNull(foundUser);
            assertEquals("testuser", foundUser.getUsername());
            assertEquals("test@example.com", foundUser.getEmail());
        }

        @Test
        void findById_returns_null_if_user_not_found() {
            // GIVEN: A user with the specified ID does not exist in the repository.
            // WHEN: The findById() method is called with the ID.
            // THEN: Null is returned.
            assertNull(repository.findById("nonexistentid"));
        }

        @Test
        void insert_returns_new_user() {
            // GIVEN: A new user object is created.
            // WHEN: The insert() method is called with the user object.
            // THEN: The user object is added to the repository, and the user object is returned.
            User insertedUser = repository.insert(new User("newuser", "newtest", "new@example.com", "newpassword"));
            assertEquals("newuser", insertedUser.getUsername());
            assertEquals("new@example.com", insertedUser.getEmail());
        }

        @Test
        void replace_returns_null_if_user_exists() {
            // GIVEN: A user with the specified ID exists in the repository.
            // WHEN: The replace() method is called with the ID and a new user object.
            // THEN: The user object is replaced in the repository, and null is returned.
            User replacedUser = repository.replace("user```java
            assertEquals("updateduser", replacedUser.getUsername());
            assertEquals("updated@example.com", replacedUser.getEmail());
        }

        @Test
        void replace_returns_null_if_user_not_found() {
            // GIVEN: A user with the specified ID does not exist in the repository.
            // WHEN: The replace() method is called with the ID and a new user object.
            // THEN: A RuntimeException is thrown with the message "Data does not exist.".
            assertThrows(RuntimeException.class, () -> repository.replace("nonexistentid", new User("updateduser", "updatedtest", "updated@example.com", "updatedpassword")));
        }

        @Test
        void removeById_returns_true_if_user_exists() {
            // GIVEN: A user with the specified ID exists in the repository.
            // WHEN: The removeById() method is called with the ID.
            // THEN: The user object is removed from the repository, and true is returned.
            assertTrue(repository.removeById("user123"));
            assertNull(repository.findById("user123"));
        }

        @Test
        void removeById_returns_true_if_user_not_found() {
            // GIVEN: A user with the specified ID does not exist in the repository.
            // WHEN: The removeById() method is called with the ID.
            // THEN: The user object is not removed from the repository, and true is returned.
            assertTrue(repository.removeById("nonexistentid"));
        }
    }
}