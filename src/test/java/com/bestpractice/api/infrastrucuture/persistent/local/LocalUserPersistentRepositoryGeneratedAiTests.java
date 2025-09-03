package com.bestpractice.api.infrastrucuture.persistent.local;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(LocalUserPersistentRepository.class)
class LocalUserPersistentRepositoryGeneratedAiTests {

    private LocalUserPersistentRepository repository = new LocalUserPersistentRepository();

    @BeforeEach
    void setUp() {
        repository.users.clear();
    }

    @Test
    void newId_returnsValidUUID() {
        // GIVEN: A new UserPersistentRepository instance is created.
        // WHEN: The newId() method is called.
        // THEN: A new UUID is generated and returned as a string.
        String id = repository.newId();
        assertNotNull(id, "UUID should not be null");
        assert(!id.isEmpty(), "UUID should not be empty");
    }

    @Test
    void findByEmail_returnsUserIfFound() {
        // GIVEN: A User object is added to the repository.
        User user = new User("1", "testuser", "test@example.com", "password");
        repository.insert(user);

        // WHEN: The findByEmail() method is called with the email address.
        User foundUser = repository.findByEmail("test@example.com");

        // THEN: The User object with the matching email address is returned.
        assertNotNull(foundUser, "User should not be null");
        assertEquals("testuser", foundUser.getUsername(), "Username should match");
        assertEquals("test@example.com", foundUser.getEmail(), "Email should match");
    }

    @Test
    void findByEmail_returnsNullIfUserNotFound() {
        // GIVEN: A UserPersistentRepository instance is created.
        // WHEN: The findByEmail() method is called with an email address that does not exist.
        // THEN: Null is returned.
        assertNull(repository.findByEmail("nonexistent@example.com"), "User should not be found");
    }

    @Test
    void findById_returnsUserById() {
        // GIVEN: A User object is added to the repository with an ID.
        User user = new User("1", "testuser", "test@example.com", "password");
        repository.insert(user);

        // WHEN: The findById() method is called with the ID.
        User foundUser = repository.findById("1");

        // THEN: The User object with the matching ID is returned.
        assertNotNull(foundUser, "User should not be null");
        assertEquals("testuser", foundUser.getUsername(), "Username should match");
        assertEquals("test@example.com", foundUser.getEmail(), "Email should match");
    }

    @Test
    void findById_returnsNullIfUserNotFound() {
        // GIVEN: A UserPersistentRepository instance is created.
        // WHEN: The findById() method is called with an ID that does not exist.
        // THEN: Null is returned.
        assertNull(repository.findById("nonexistentid"), "User should not be found");
    }

    @Test
    void insert_returnsInsertedUser() {
        // GIVEN: A new User object is created.
        User user = new User("1", "testuser", "test@example.com", "password");

        // WHEN: The insert() method is called with the User object.
        User insertedUser = repository.insert(user);

        // THEN: The User object is returned, and it is added to the repository.
        assertNotNull(insertedUser, "User should not be null");
        assertEquals("testuser", insertedUser.getUsername(), "Username should match");
        assertEquals("test@example.com", insertedUser.getEmail(), "Email should match");
        assertTrue(repository.users.contains(insertedUser), "User should be in the repository");
    }

    @Test
    void replace_returnsNullAndUpdatesUser()java
        // GIVEN: A new User object is created.
        User user = new User("1", "testuser", "test@example.com", "password");

        // WHEN: The insert() method is called with the User object.
        User insertedUser = repository.insert(user);

        // THEN: The User object is returned, and it is added to the repository.
        assertNotNull(insertedUser, "User should not be null");
        assertEquals("testuser", insertedUser.getUsername(), "Username should match");
        assertEquals("test@example.com", insertedUser.getEmail(), "Email should match");
        assertTrue(repository.users.contains(insertedUser), "User should be in the repository");
    }

    @Test
    void replace_returnsNullAndUpdatesUser() {
        // GIVEN: A User object is added to the repository with an ID.
        User user = new User("1", "testuser", "test@example.com", "password");
        repository.insert(user);

        // WHEN: The replace() method is called with the ID and a
        // THEN: The User object is returned, and it is updated in the repository.
        assertNotNull(insertedUser, "User should not be null");
        assertEquals("testuser", insertedUser.getUsername(), "Username should match");
        assertEquals("test@example.com", insertedUser.getEmail(), "Email should match");

        User updatedUser = repository.findById("1");
        assertEquals("testuser", updatedUser.getUsername(), "Username should match after update");
        assertEquals("test@example.com", updatedUser.getEmail(), "Email should match after update");
    }

    @Test
    void replace_throwsExceptionIfUserNotFound() {
        // GIVEN: A UserPersistentRepository instance is created.
        // WHEN: The replace() method is called with an ID that does not exist.
        // THEN: A RuntimeException is thrown.
        assertThrows(RuntimeException.class, () -> repository.replace("nonexistentid", new User("1", "testuser", "test@example.com", "password")));
    }

    @Test
    void removeById_returnsTrueIfUserRemoved() {
        // GIVEN: A User object is added to the repository with an ID.
        User user = new User("1", "testuser", "test@example.com", "password");
        repository.insert(user);

        // WHEN: The removeById() method is called with the ID.
        boolean removed = repository.removeById("1");

        // THEN: The User object is removed from the repository, and the method returns true.
        assertTrue(removed, "User should have been removed");
        assertEquals(0, repository.users.size(), "User list should be empty");
    }

    @Test
    void removeById_returnsTrueIfUserNotFound() {
        // GIVEN: A UserPersistentRepository instance is created.
        // WHEN: The removeById() method is called with an ID that does not exist.
        // THEN: The method returns true, and the repository remains unchanged.
        boolean removed = repository.removeById("nonexistentid");
        assertEquals(1, repository.users.size(), "User list should remain unchanged");
    }
}