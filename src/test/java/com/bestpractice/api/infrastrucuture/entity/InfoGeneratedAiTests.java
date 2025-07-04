package com.bestpractice.api.infrastrucuture.entity;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ReflectiveOperationsMethodInvocation;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.extension.ReflectiveOperationsMethodInvocation.ReflectiveOperationsMethodInvocation.forMethod;

@org.junit.jupiter.api.extension.ExtendWith(forMethod(Info.class.getMethod("setId", String.class)))
public class InfoGeneratedAiTests {

    private Info info;

    @BeforeEach
    void setUp() {
        info = new Info();
    }

    @Test
    void setId() {
        // GIVEN a new Info object
        // WHEN the setId method is called with a value "testId"
        // THEN the id field of the Info object should be set to "testId"
        info.setId("testId");
        // THEN the id field of the Info object should be "testId"
        assertEquals("testId", info.getId());
    }

    @Test
    void getTitle() {
        // GIVEN a new Info object
        // WHEN the getTitle method is called
        // THEN the title field of the Info object should be returned
        info.setTitle("testTitle");
        // THEN the title field of the Info object should be "testTitle"
        assertEquals("testTitle", info.getTitle());
    }

    @Test
    void getTitle_when_title_is_not_set() {
        // GIVEN a new Info object
        // WHEN the getTitle method is called
        // THEN the title field of the Info object should be null
        info.setTitle(null);
        // THEN the title field of the Info object should be null
        assertEquals(null, info.getTitle());
    }

    @Test
    void getDescription() {
        // GIVEN a new Info object
        // WHEN the getDescription method is called
        // THEN the description field of the Info object should be returned
        info.setDescription("testDescription");
        // THEN the description field of the Info object should be "testDescription"
        assertEquals("testDescription", info.getDescription());
    }

    @Test
    void getDescription_when_description_is_not_set() {
        // GIVEN a new Info object
        // WHEN the getDescription method is called
        // THEN the description field of the Info object should be null
        info.setDescription(null);
        // THEN the description field of the Info object should be null
        assertEquals(null, info.getDescription());
    }
}
