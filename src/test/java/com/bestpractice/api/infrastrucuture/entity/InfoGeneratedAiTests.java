package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InfoGeneratedAiTests {

    private Info info;

    @BeforeEach
    void setUp() {
        info = new Info();
    }

    @Test
    void testGetSetId() {
        String expectedId = "123";
        info.setId(expectedId);
        assertEquals(expectedId, info.getId());
    }

    @Test
    void testGetSetTitle() {
        String expectedTitle = "Test Title";
        info.setTitle(expectedTitle);
        assertEquals(expectedTitle, info.getTitle());
    }

    @Test
    void testGetSetDescription() {
        String expectedDescription = "Test Description";
        info.setDescription(expectedDescription);
        assertEquals(expectedDescription, info.getDescription());
    }
}
