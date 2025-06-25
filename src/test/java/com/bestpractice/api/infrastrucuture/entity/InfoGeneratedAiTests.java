package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ReflectiveOperationsMethodInvocation;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.extension.ReflectiveOperationsMethodInvocation.ReflectiveOperationsMethodInvocation.forMethod;

public class InfoGeneratedAiTests {

    private Info info;

    @BeforeEach
    void setUp() {
        info = new Info();
    }

    @Test
    void setId() {
        // GIVEN a new Info object
        // WHEN the setId method is called with a non-null string value
        // THEN the id field of the Info object should be set to the provided string value
        info.setId("testId");
        // THEN
        String id = info.getId();
        assertEquals("testId", id);
    }

    @Test
    void getTitle() {
        // GIVEN a new Info object
        // WHEN the getTitle method is called
        // THEN the title field of the Info object should be returned
        info.setTitle("testTitle");
        // THEN
        String title = info.getTitle();
        assertEquals("testTitle", title);
    }

    @Test
    void getTitle_null() {
        // GIVEN a new Info object
        // WHEN the getTitle method is called
        // THEN the title field of the Info object should be returned
        info.setTitle(null);
        // THEN
        String title = info.getTitle();
        assertNull(title);
    }

    @Test
    void getDescription() {
        // GIVEN a new Info object
        // WHEN the getDescription method is called
        // THEN the description field of the Info object should be returned
        info.setDescription("testDescription");
        // THEN
        String description = info.getDescription();
        assertEquals("testDescription", description);
    }

    @Test
    void getDescription_null() {
        // GIVEN a new Info object
        // WHEN the getDescription method is called
        // THEN the description field of the Info object should be returned
        info.setDescription(null);
        // THEN
        String description = info.getDescription();
        assertNull(description);
    }
}