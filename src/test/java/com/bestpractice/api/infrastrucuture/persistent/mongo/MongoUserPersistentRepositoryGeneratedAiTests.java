package com.bestpractice.api.infrastrucuture.persistent.mongo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

User user = new User("test", "test", "test@example.com", "password");
        MongoUserPersistentRepository.this.replace("65432123456789012345678", user);
        assertNotNull(replacedUser, "Replaced user should not be null");
        assertEquals("test", replacedUser.getUsername(), "Username should match");
        assertEquals("test@example.com", replacedUser.getEmail(), "Email should match");
    }
