package com.bestpractice.api.infrastrucuture.entity;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({})
public class InfoGeneratedAiTests {

    private Info info;

    @BeforeEach
    public void setUp() {
        info = new Info();
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        String id = "123";

        // WHEN
        info.setId(id);

        // THEN
        assertEquals(id, info.getId());
    }

    @Test
    public void testSetAndGetTitle() {
        // GIVEN
        String title = "Sample Title";

        // WHEN
        info.setTitle(title);

        // THEN
        assertEquals(title, info.getTitle());
    }

    @Test
    public void testSetAndGetDescription() {
        // GIVEN
        String description = "Sample Description";

        // WHEN
        info.setDescription(description);

        // THEN
        assertEquals(description, info.getDescription());
    }
}
