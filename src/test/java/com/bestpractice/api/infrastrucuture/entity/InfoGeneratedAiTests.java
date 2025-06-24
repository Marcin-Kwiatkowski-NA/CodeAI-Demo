package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.extension.ReflectiveOperationsInvocationHandler.getInstance;

@org.junit.jupiter.api.ExtensionRegistry.registerExtension(ReflectiveOperationsInvocationHandler.class)
class InfoGeneratedAiTests {

    private Info info;

    @BeforeEach
    void setUp() {
        info = new Info();
    }

    @Test
    void testGetAndSetId() {
        // GIVEN: A new Info object is created.
        // WHEN: The id is set to "123".
        info.setId("123");
        // THEN: The id is "123".
        assertEquals("123", info.getId());
    }

    @Test
    void testGetAndSetTitle() {
        // GIVEN: A new Info object is created.
        // WHEN: The title is set to "My Title".
        info.setTitle("My Title");
        // THEN: The title is "My Title".
        assertEquals("My Title", info.getTitle());
    }

    @Test
    void testGetAndSetDescription() {
        // GIVEN: A new Info object is created.
        // WHEN: The description is set to "My Description".
        info.setDescription("My Description");
        // THEN: The description is "My Description".
        assertEquals("My Description", info.getDescription());
    }
}
