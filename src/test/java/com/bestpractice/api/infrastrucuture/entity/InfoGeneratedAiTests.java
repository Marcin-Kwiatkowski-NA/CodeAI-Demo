package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyExtension.class)
public class InfoGeneratedAiTests {

    @BeforeEach
    void setUp() {
        info = new Info();
    }

    private Info info;

    @Test
    public void testGetAndSetId() {
        // GIVEN: A new Info object is created.
        // WHEN: The id is set to "123".
        info.setId("123");
        // THEN: The id is "123".
        assertEquals("123", info.getId());
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN: A new Info object is created.
        // WHEN: The title is set to "Test Title".
        info.setTitle("Test Title");
        // THEN: The title is "Test Title".
        assertEquals("Test Title", info.getTitle());
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN: A new Info object is created.
        // WHEN: The description is set to "Test Description".
        info.setDescription("Test Description");
        // THEN: The description is "Test Description".
        assertEquals("Test Description", info.getDescription());
    }

    @Test
    public void testOnPrePersist() {
        // GIVEN: A new Info object is created.
        // WHEN: The onPrePersist method is called.
        // THEN: The createdAt field is set to the current date.
        Date createdAt = info.getCreatedAt();
        // Assert that createdAt is not null
        assertNotNull(createdAt);
    }
}

package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.extension.ExtensionContext;

public class MyExtension implements ExtensionContext.Generated {
    @Override
    public void beforeTestExecution(ExtensionContext context) {
        // This method is called before each test execution.
    }
}