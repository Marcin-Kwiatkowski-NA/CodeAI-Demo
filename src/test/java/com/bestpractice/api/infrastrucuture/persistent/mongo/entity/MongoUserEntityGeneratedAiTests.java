package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

        user.setId(mongoUserEntity.getId().toString());

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
        user.setUsername(mongoUserEntity.getUsername());
        user.setPassword(mongoUserEntity.getPassword());
        assertEquals("testUser", user.getUsername());
        assertEquals("email@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
    }
