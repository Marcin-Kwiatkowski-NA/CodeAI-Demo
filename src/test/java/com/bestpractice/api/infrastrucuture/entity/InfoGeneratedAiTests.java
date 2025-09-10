package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ReflectTestUtils;

package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ReflectTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.extension.ReflectTestUtils.reflectField;

@org.junit.jupiter.api.extension.ExtendWith(ReflectTestUtils.class)
class InfoGeneratedAiTests {

    private Info info;

    @BeforeEach
    void setUp() {
        info = new Info();
    }

    @Test
    void getId() {
        // GIVEN a new Info object
        // WHEN get the id
        // THEN the id should be returned
        String id = info.getId();
        assertEquals(null, id);
    }

    @Test
    void getTitle() {
        // GIVEN a new Info object
        // WHEN get the title
        // THEN the title should be returned
        String title = info.getTitle();
        assertEquals(null, title);
    }

    @Test
    void getDescription() {
        // GIVEN a new Info object
        // WHEN get the description
        // THEN the description should be returned
        String description = info.getDescription();
        assertEquals(null, description);
    }

    @Test
    void setId() {
        // GIVEN a new Info object
        // WHEN set the id
        // THEN the id should be set
        info.setId("testId");
        assertEquals("testId", info.getId());
    }

    @Test
    void setTitle() {
        // GIVEN a new Info object
        // WHEN set the title
        // THEN the title should be set
        info.setTitle("testTitle");
        assertEquals("testTitle", info.getTitle());
    }

    @Test
    void setDescription() {
        // GIVEN a new Info object
        // WHEN set the description
        // THEN the description should be set
        info.setDescription("testDescription");
        assertEquals("testDescription", info.getDescription());
    }
}
