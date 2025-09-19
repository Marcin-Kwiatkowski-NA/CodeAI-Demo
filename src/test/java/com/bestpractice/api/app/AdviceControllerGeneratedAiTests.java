package com.bestpractice.api.app;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

assertEquals(500, res.getStatus());
        assertEquals("Internal server error", res.getError());
        assertEquals("Internal server error", res.getMessage());
    }
}
