package com.bestpractice.api.infrastrucuture;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

Mockito.verify(rdbmsUserPersistentRepository, Mockito.times(1)).findById(id);
        assertNotNull(result);
        assertEquals(title, result.getUsername());
        assertEquals(description, result.getEmail());
        assertEquals("password", result.getPassword());
    }
