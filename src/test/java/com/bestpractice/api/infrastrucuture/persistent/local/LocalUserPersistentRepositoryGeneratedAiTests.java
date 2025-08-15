package com.bestpractice.api.infrastrucuture.persistent.local;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

assert repository.users.isEmpty();
    }

    @Test
    void removeById_returnsTrueWhenNotFound() {
        User user = new User("1", "test", "test@example.com", "password");
        repository.users.add(user);
        boolean removed = repository.removeById("99");
        assert removed;
        assert !repository.users.isEmpty();
    }
}
