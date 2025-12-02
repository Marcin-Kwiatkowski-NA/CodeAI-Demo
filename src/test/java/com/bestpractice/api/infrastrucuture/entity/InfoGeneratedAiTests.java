package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InfoGeneratedAiTests {

    private Info info;

    @BeforeEach
    void setUp() {
        info = new Info();
    }

    @Test
    void givenValidId_whenSetId_thenIdIsSetCorrectly() {
        // GIVEN
        String id = "12345";

        // WHEN
        info.setId(id);

        // THEN
        assertEquals("12345", info.getId());
    }

    @Test
    void givenValidTitle_whenSetTitle_thenTitleIsSetCorrectly() {
        // GIVEN
        String title = "Test Title";

        // WHEN
        info.setTitle(title);

        // THEN
        assertEquals("Test Title", info.getTitle());
    }

    @Test
    void givenValidDescription_whenSetDescription_thenDescriptionIsSetCorrectly() {
        // GIVEN
        String description = "Test Description";

        // WHEN
        info.setDescription(description);

        // THEN
        assertEquals("Test Description", info.getDescription());
    }

    @Test
    void givenSharedData_whenOnPrePersist_thenCreatedAtIsSet() {
        // GIVEN
        Date beforePersist = new Date();

        // WHEN
        info.onPrePersist();

        // THEN
        assertNotNull(info.getCreatedAt());
        assertEquals(true, info.getCreatedAt().after(beforePersist) || info.getCreatedAt().equals(beforePersist));
    }

    @Test
    void givenNullId_whenSetId_thenIdIsSetToNull() {
        // GIVEN
        String id = null;

        // WHEN
        info.setId(id);

        // THEN
        assertEquals(null, info.getId());
    }

    @Test
    void givenEmptyTitle_whenSetTitle_thenTitleIsSetCorrectly() {
        // GIVEN
        String title = "";

        // WHEN
        info.setTitle(title);

        // THEN
        assertEquals("", info.getTitle());
    }

    @Test
    void givenEmptyDescription_whenSetDescription_thenDescriptionIsSetCorrectly() {
        // GIVEN
        String description = "";

        // WHEN
        info.setDescription(description);

        // THEN
        assertEquals("", info.getDescription());
    }

    @Test
    void givenNullTitle_whenSetTitle_thenTitleIsSetToNull() {
        // GIVEN
        String title = null;

        // WHEN
        info.setTitle(title);

        // THEN
        assertEquals(null, info.getTitle());
    }

    @Test
    void givenNullDescription_whenSetDescription_thenDescriptionIsSetToNull() {
        // GIVEN
        String description = null;

        // WHEN
        info.setDescription(description);

        // THEN
        assertEquals(null, info.getDescription());
    }
}
