package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InfoGeneratedAiTests {

    private Info info;

    @BeforeEach
    void setUp() {
        info = new Info();
    }

    @Test
    void testSetAndGetId() {
        // GIVEN
        String expectedId = "12345";
        // WHEN
        info.setId(expectedId);
        String actualId = info.getId();
        // THEN
        assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    void testSetAndGetTitle() {
        // GIVEN
        String expectedTitle = "Test Title";
        // WHEN
        info.setTitle(expectedTitle);
        String actualTitle = info.getTitle();
        // THEN
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    void testSetAndGetDescription() {
        // GIVEN
        String expectedDescription = "Test Description";
        // WHEN
        info.setDescription(expectedDescription);
        String actualDescription = info.getDescription();
        // THEN
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    void testCreatedAtIsNullBeforePersist() {
        // GIVEN
        // WHEN
        Date createdAt = info.getCreatedAt();
        // THEN
        assertThat(createdAt).isNull();
    }

    @Test
    void testCreatedAtIsSetOnPrePersist() {
        // GIVEN
        // WHEN
        info.onPrePersist();
        Date createdAt = info.getCreatedAt();
        // THEN
        assertThat(createdAt).isNotNull();
        assertThat(createdAt.getTime()).isBetween(
                System.currentTimeMillis() - 1000,
                System.currentTimeMillis() + 1000
        );
    }

    @Test
    void testCreatedAtCanBeSetManually() {
        // GIVEN
        Date customDate = new Date(1000000000L);
        // WHEN
        info.setCreatedAt(customDate);
        Date actualDate = info.getCreatedAt();
        // THEN
        assertEquals(customDate, actualDate);
    }
}
