package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

public class MongoInfoEntityGeneratedAiTests {

    private Info info;

    @BeforeEach
    void setUp() {
        info = new Info();
    }

    @Test
    void createInfo() {
        info.setName("John Doe");
        info.setAge(30);

        assertEquals("John Doe", info.getName());
        assertEquals(30, info.getAge());
    }

    @Test
    void createInfoWithNullValues() {
        info.setName(null);
        info.setAge(0);

        assertEquals(null, info.getName());
        assertEquals(0, info.getAge());
    }
}
