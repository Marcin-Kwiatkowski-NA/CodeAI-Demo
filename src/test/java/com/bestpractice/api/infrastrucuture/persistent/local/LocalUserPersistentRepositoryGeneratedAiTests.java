package com.bestpractice.api.infrastrucuture.persistent.local;

    @Test

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
    void replace_returnsNullIfUserExists() {
        // GIVEN: A user with a specific ID is stored in the repository.
        LocalUserPersistentRepository repository = new LocalUserPersistentRepository();
        User user = new User("1", "test", "test@example.com", "password");
        repository.users.add(user);

        // WHEN: The replace("1", user) method is called.
        // THEN: The user object is added to the user list, and the same user object is returned.
        User insertedUser = repository.insert(user);
        assert insertedUser != null;
        assert repository.users.contains(insertedUser);
    }
