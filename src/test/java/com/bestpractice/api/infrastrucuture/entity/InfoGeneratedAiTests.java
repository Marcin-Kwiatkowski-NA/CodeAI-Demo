package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        // THEN the id field is "testId"
        assertEquals("testId", info.getId());
    }

    @Test
    void getTitle() {
        // GIVEN a new Info object
        // WHEN the getTitle method is called
        // THEN the title field of the Info object should be returned
        String title = info.getTitle();
        // THEN the title field is "testTitle"
        assertEquals("testTitle", title);
    }

    @Test
    void getDescription() {
        // GIVEN a new Info object
        // WHEN the getDescription method is called
        // THEN the description field of the Info object should be returned
        String description = info.getDescription();
        // THEN the description field is "testDescription"
        assertEquals("testDescription", description);
    }
}
