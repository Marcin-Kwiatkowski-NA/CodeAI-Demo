package com.bestpractice.api.infrastrucuture.persistent.local;

        assertEquals("test@example.com", insertedUser.getEmail(), "Email should match");

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
        assertEquals("password", insertedUser.getPassword(), "Password should match");
    }

    @Test
    void replace_returnsNullIfUserExists() {
        // GIVEN: A user with a specific ID exists.
        User user = new User("user1", "test", "test@example.com", "password");
        repository.users.add(user);

        // WHEN: The replace("user1", new User("user1", "test", "test@example.com", "newPassword")) method is called.
        // THEN: The user with the specified ID is replaced and null is returned.
        User replacedUser = repository.replace("user1", new User("user1", "test", "test@example.com", "newPassword"));
        assertNull(replacedUser, "Returned user should be null");
        assertEquals("user1", replacedUser.getId(), "ID should match");
        assertEquals("test", replacedUser.getUsername(), "Username should match");
        assertEquals("test@example.com", replacedUser.getEmail(), "Email should match");
        assertEquals("newPassword", replacedUser.getPassword(), "Password should match");
    }

    @Test
    void replace_returnsNullIfUserDoesNotExist() {
        // GIVEN: No user exists with a specific ID.
        // WHEN: The replace("nonexistentID", new User("user1", "test", "test@example.com", "password")) method is called.
        // THEN: Null is returned.
        User replacedUser = repository.replace("nonexistentID", new User("user1", "test", "test@example.com", "password"));
        assertNull(replacedUser, "Returned user should be null");
    }

    @Test
    void removeById_returnsTrueIfUserRemoved() {
        // GIVEN: A user with a specific ID exists.
        User user = new User("user1", "test", "test@example.com", "password");
        repository.users.add(user);

        // WHEN: The removeById("user1") method is called.
        // THEN: The user with the specified ID is removed from the list and true is returned.
        boolean removed = repository.removeById("user1");
        assertTrue(removed, "Removal should be successful");
        assertEquals(0, repository.users.size(), "User list should be empty");
    }

    @Test
    void removeById_returnsTrueIfUserDoesNotExist() {
        // GIVEN: No user exists with a specific ID.
        // WHEN: The removeById("nonexistentID") method is called.
        // THEN: True is returned.
        boolean removed = repository.removeById("nonexistentID");
        assertTrue(removed, "Removal should be successful");
        assertEquals(1, repository.users.size(), "User list should remain unchanged");
    }
}
