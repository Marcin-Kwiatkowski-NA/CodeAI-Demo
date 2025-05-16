package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.junit.jupiter.api.BeforeEach;
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
    public void testGetSetId() {
        // GIVEN: A new Info object is created.
        // WHEN: The id is set to "123".
        info.setId("123");
        // THEN: The id is "123".
        assertEquals("123", info.getId());
    }

    @Test
    public void testGetSetTitle() {
        // GIVEN: A new Info object is created.
        // WHEN: The title is set to "Test Title".
        info.setTitle("Test Title");
        // THEN: The title is "Test Title".
        assertEquals("Test Title", info.getTitle());
    }

    @Test
    public void testGet setDescription() {
        // GIVEN: A new Info object is created.
        // WHEN: The description is set to "Test Description".
        info.setDescription("Test Description");
        // THEN: The description is "Test Description".
        assertEquals("Test Description", info.getDescription());
    }
}

class MyExtension implements org.junit.jupiter.api.extension.Extension {
    @Override
    public void tearDown() {
    }
}
