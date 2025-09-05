package com.bestpractice.api.infrastrucuture.persistent.local;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

User user = new User("1", "test", "test@example.com", "password");
            repository.insert(user);
            User replacedUser = repository.replace("1", updatedUser);
            assertNotNull(replacedUser);
            assertEquals("1", replacedUser.getId());
            assertEquals("newTest", replacedUser.getUsername());
            assertEquals("newTest@example.com", replacedUser.getEmail());
            assertEquals("newPassword", replacedUser.getPassword());
        }

        @Test
        void replace_throwsExceptionIfUserDoesNotExist() {
            try {
                repository.replace("2", new User("2", "test", "test@example.com", "password"));
                fail("Expected a RuntimeException to be thrown");
            } catch (RuntimeException e) {
                assertEquals("Data does not exist.", e.getMessage());
            }
        }

        @Test
        void removeById_returnsTrueIfUserRemoved() {
            User user = new User("1", "test", "test@example.com", "password");
            repository.insert(user);
            boolean removed = repository.removeById("1");
            assertTrue(removed);
            assertFalse(repository.findById("1").isPresent());
        }

        @Test
        void removeById_returnsTrueIfUserDoesNotExist() {
            boolean removed = repository.removeById("2");
            assertTrue(removed);
        }
    }
}
