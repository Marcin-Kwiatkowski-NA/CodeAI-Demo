package com.bestpractice.api.infrastrucuture.persistent.mongo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

"test", "test@example.com", "password");
        // WHEN: The replace() method is called with that ID and the same User object.
        // THEN: The User object is replaced in the database, and the same User object is returned.
        User replacedUser = mongoUserPersistentRepository.replace("789", user);
        assertNotNull(replacedUser, "Replaced user should not be null");
        assertEquals("789", replacedUser.getId(), "ID should match");
        assertEquals("test", replacedUser.getUsername(), "Username should match");
        assertEquals("test@example.com", replacedUser.getEmail(), "Email should match");
        assertEquals("password", replacedUser.getPassword(), "Password should match");
    }

    @Test
    void removeById_removes_user_if_found() {
        // GIVEN: A MongoUserEntity is stored in the database with a specific ID.
        User user = new User("789", "test", "test@example.com", "password");
        // WHEN: The removeById() method is called with that ID.
        // THEN: The MongoUserEntity corresponding to that ID is deleted from the database, and true is returned.
        boolean result = mongoUserPersistentRepository.removeById("789");
        assertTrue(result, "Removal should be acknowledged");
    }
}
