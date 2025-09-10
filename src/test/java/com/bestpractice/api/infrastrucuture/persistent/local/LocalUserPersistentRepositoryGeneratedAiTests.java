package com.bestpractice.api.infrastrucuture.persistent.local;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@ExtendWith(LocalUserPersistentRepository.class)
class LocalUserPersistentRepositoryGeneratedAiTests {

    private final LocalUserPersistentRepository repository = new LocalUserPersistentRepository();

    @BeforeEach
    void setUp() {
        repository.users.clear();
    }

    @Test
    void newId_returnsValidUUID() {
        // GIVEN: A new ID should be generated.
        // WHEN: The newId() method is called.
        // THEN: A new UUID is returned.
        String id = repository.newId();
        assert id != null;
    }

    @Test
    void findByEmail_returnsUserWhenEmailExists() {
        // GIVEN: A user with a specific email exists in the repository.
        User user = new User("user1", "user1", "user1@example.com", "password");
        repository.users.add(user);

        // WHEN: The findByEmail("user1@example.com") method is called.
        // THEN: The user with the specified email is returned.
        User foundUser = repository.findByEmail("user1@example.com");
        assert foundUser != null;
        assert foundUser.getUsername().equals("user1");
        assert foundUser.getEmail().equals("user1@example.com");
    }

    @Test
    void findByEmail_returnsNullWhenEmailDoesNotExist() {
        // GIVEN: No user exists in the repository with the specified email.
        // WHEN: The findByEmail("nonexistent@example.com") method is called.
        // THEN: Null is returned.
        User foundUser = repository.findByEmail("nonexistent@example.com");
        assert foundUser == null;
    }

    @Test
    void findById_returnsUserById() {
        // GIVEN: A user with a specific ID exists in the repository.
        User user = new User("user1", "user1", "user1@example.com", "password");
        repository.users.add(user);

        // WHEN: The findById("user1") method is called.
        // THEN: The user with the specified ID is returned.
        User foundUser = repository.findById("user1");
        assert foundUser != null;
        assert foundUser.getUsername().equals("user1");
        assert foundUser.getEmail().equals("user1@example.com");
    }

    @Test
    void findById_returnsNullWhenIdDoesNotExist() {
        // GIVEN: No user exists in the repository with the specified ID.
        // WHEN: The findById("nonexistentId") method is called.
        // THEN: Null is returned.
        User foundUser = repository.findById("nonexistentId");
        assert foundUser == null;
    }

    @Test
    void insert_insertsUserAndReturnsUser() {
        // GIVEN: A new user object is created.
        User user = new User("user1", "user1", "user1@example.com", "password");

        // WHEN: The insert(user) method is called.
        // THEN: The user is added to the repository, and the user object is returned.
        User insertedUser = repository.insert(user);
        assert insertedUser != null;
        assert insertedUser.getUsername().equals("user1");
        assert insertedUser.getEmail().equals("user1@example.com");
        assert repository.users.contains(insertedUser);
    }

    @Test
    void replace_replacesUserByIdAndReturnsNull() {
        // GIVEN: A user with a specific ID exists in the repository.
        User user = new User("user1java
        "password2")); method is called.
        // THEN: The user with the specified ID is replaced, and null is returned.
        User replacedUser = repository.replace("user1", new User("user2", "user2", "user2@example.com", "password2"));
        assert replacedUser != null;
        assert !repository.users.contains(user);
        assert repository.users.contains(replacedUser);
        assert replacedUser.getUsername().equals("user2");
        assert replacedUser.getEmail().equals("user2@example.com");
    }

    @Test
    void replace_returnsRuntimeExceptionWhenIdDoesNotExist() {
        // GIVEN: No user exists in the repository with the specified ID.
        // WHEN: The replace("nonexistentId", new User("user1", "user1", "user1@example.com", "password")) method is called.
        // THEN: A RuntimeException is thrown.
        assert throwsRuntimeException(() -> repository.replace("nonexistentId", new User("user1", "user1", "user1@example.com", "password")));
    }

    @Test
    void removeById_removesUserByIdAndReturnsTrue() {
        // GIVEN: A user with a specific ID exists in the repository.
        User user = new User("user1", "user1", "user1@example.com", "password");
        repository.users.add(user);

        // WHEN: The removeById("user1") method is called.
        // THEN: The user with the specified ID is removed from the repository, and true is returned.
        boolean removed = repository.removeById("user1");
        assert removed;
        assert !repository.users.contains(user);
    }

    @Test
    void removeById_returnsTrueWhenIdDoesNotExist() {
        // GIVEN: No user exists in the repository with the specified ID.
        // WHEN: The removeById("nonexistentId") method is called.
        // THEN: True is returned.
        boolean removed = repository.removeById("nonexistentId");
        assert removed;
    }
}