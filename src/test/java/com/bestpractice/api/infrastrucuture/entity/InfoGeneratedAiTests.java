package com.bestpractice.api.infrastrucutructure.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MyOpenApiExtension.class)
public class InfoGeneratedAiTests {

    private Info info;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetSetId() {
        // GIVEN: A new Info object is created.
        // WHEN: The id is set to "123".
        info.setId("123");
        // THEN: The id is "123".
        assertEquals("123", info.getId());
    }

    @Test
    void testGetSetTitle() {
        // GIVEN: A new Info object is created.
        // WHEN: The title is set to "Example Title".
        info.setTitle("Example Title");
        // THEN: The title is "Example Title".
        assertEquals("Example Title", info.getTitle());
    }

    @Test
    void testGet setDescription() {
        // GIVEN: A new Info object is created.
        // WHEN: The description is set to "Example Description".
        info.setDescription("Example Description");
        // THEN: The description is "Example Description".
        assertEquals("Example Description", info.getDescription());
    }
}

class MyOpenApiExtension implements org.junit.jupiter.api.extension.Extension {
    @Override
    public void tearDown() {
    }
}
