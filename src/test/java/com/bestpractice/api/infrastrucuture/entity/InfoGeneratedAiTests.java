package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.*;

class InfoGeneratedAiTests {

    private Info info;

    @BeforeEach
    void setUp() {
        info = new Info();
    }

    @Test
    void testIdGetterAndSetter() {
        // GIVEN
        String expectedId = "12345";

        // WHEN
        info.setId(expectedId);

        // THEN
        assertThat(info.getId()).isEqualTo(expectedId);
    }

    @Test
    void testTitleGetterAndSetter() {
        // GIVEN
        String expectedTitle = "Test Title";

        // WHEN
        info.setTitle(expectedTitle);

        // THEN
        assertThat(info.getTitle()).isEqualTo(expectedTitle);
    }

    @Test
    void testDescriptionGetterAndSetter() {
        // GIVEN
        String expectedDescription = "Test Description";

        // WHEN
        info.setDescription(expectedDescription);

        // THEN
        assertThat(info.getDescription()).isEqualTo(expectedDescription);
    }

    @Test
    void testCreatedAtIsSetByOnPrePersist() {
        // GIVEN
        Date beforePersist = new Date();

        // WHEN
        info.onPrePersist();

        // THEN
        Date createdAt = info.getCreatedAt();
        assertThat(createdAt).isNotNull();
        assertThat(createdAt).isAfterOrEqualTo(beforePersist);
        assertThat(createdAt).isCloseTo(new Date(), within(1, ChronoUnit.SECONDS));
    }

    @Test
    void testCreatedAtCanBeSetManually() {
        // GIVEN
        Date customDate = Date.from(Instant.parse("2025-01-01T12:00:00Z"));

        // WHEN
        info.setCreatedAt(customDate);

        // THEN
        assertThat(info.getCreatedAt()).isEqualTo(customDate);
    }

    @Test
    void testCreatedAtIsNullInitially() {
        // GIVEN
        // No action needed; instance is freshly created in @BeforeEach

        // WHEN
        Date createdAt = info.getCreatedAt();

        // THEN
        assertThat(createdAt).isNull();
    }

    @Test
    void testSettingCreatedAtToNull() {
        // GIVEN
        Date customDate = Date.from(Instant.parse("2025-01-01T12:00:00Z"));
        info.setCreatedAt(customDate);

        // WHEN
        info.setCreatedAt(null);

        // THEN
        assertThat(info.getCreatedAt()).isNull();
    }
}
