package com.bestpractice.api.infrastrucuture;

        assertEquals("user1", user.getUsername());

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
        assertEquals("email1", user.getEmail());
    }

    @Test
    public void testLocalUserPersistentRepository_insert() {
        // GIVEN: No preconditions
        // WHEN: The insert method is called
        // THEN: The User object is added to the list
        var userPersistentRepository = Mockito.mock(UserPersistentRepository.class);
        var localUserPersistentRepository = new LocalUserPersistentRepository();
        var user = new User("id1", "user1", "email1", "password1");
        var insertedUser = localUserPersistentRepository.insert(user);
        assertNotNull(insertedUser);
        assertEquals("user1", insertedUser.getUsername());
        assertEquals("email1", insertedUser.getEmail());
    }

    @Test
    public void testLocalUserPersistentRepository_replace() {
        // GIVEN: A list of User objects is created
        var users = new ArrayList<>();
        var user1 = new User("id1", "user1", "email1", "password1");
        users.add(user1);

        // WHEN: The replace method is called
        // THEN: The User object with the specified id is updated
        var userPersistentRepository = Mockito.mock(UserPersistentRepository.class);
        var localUserPersistentRepository = new LocalUserPersistentRepository();
        var user = new User("id1", "user1", "email1", "password1");
        var replacedUser = localUserPersistentRepository.replace("id1", user);
        assertNotNull(replacedUser);
        assertEquals("user1", replacedUser.getUsername());
        assertEquals("email1", replacedUser.getEmail());
    }

    @Test
    public void testLocalUserPersistentRepository_removeById() {
        // GIVEN: A list of User objects is created
        var users = new ArrayList<>();
        var user1 = new User("id1", "user1", "email1", "password1");
        users.add(user1);

        // WHEN: The removeById method is called
        // THEN: The User object with the specified id is removed from the list
        var userPersistentRepository = Mockito.mock(UserPersistentRepository.class);
        var localUserPersistentRepository = new LocalUserPersistentRepository();
        var removed = localUserPersistentRepository.removeById("id1");
        assertTrue(removed);
    }
}
