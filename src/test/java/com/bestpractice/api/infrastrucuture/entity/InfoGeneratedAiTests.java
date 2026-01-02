package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class InfoGeneratedAiTests {

    private Info info;

    @BeforeEach
    void setUp() {
        info = new Info();
    }

    @Test
    void testGetSetId() {
        // GIVEN
        String expectedId = "12345";

        // WHEN
        info.setId(expectedId);
        String actualId = info.getId();

        // THEN
        assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    void testGetSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        info.setTitle(expectedTitle);
        String actualTitle = info.getTitle();

        // THEN
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    void testGetSetDescription() {
        // GIVEN
        String expectedDescription = "This is a description.";

        // WHEN
        info.setDescription(expectedDescription);
        String actualDescription = info.getDescription();

        // THEN
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    void testGetCreatedAtBeforePersist() {
        // GIVEN
        // No preconditions; the entity is newly created.

        // WHEN
        Date createdAt = info.getCreatedAt();

        // THEN
        assertThat(createdAt).isNull();
    }

    @Test
    void testOnPrePersistSetsCreatedAt() {
        // GIVEN
        // No preconditions needed; the Info instance is freshly created.

        // WHEN
        info.onPrePersist();
        Date createdAtFirst = info.getCreatedAt();

        // THEN
        assertThat(createdAtFirst).isNotNull();

        // GIVEN
        // Wait a short period to ensure a different timestamp
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // WHEN
        info.onPrePersist();
        Date createdAtSecond = info.getCreatedAt();

        // THEN
        assertThat(createdAtSecond).isNotNull();
        assertThat(createdAtSecond).isAfter(createdAtFirst);
    }
}
