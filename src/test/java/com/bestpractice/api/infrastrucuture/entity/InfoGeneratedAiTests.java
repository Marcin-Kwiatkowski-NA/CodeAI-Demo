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
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

class InfoGeneratedAiTests {

    private Info info;

    @BeforeEach
    void setUp() {
        info = new Info();
    }

    @Test
    void testSetAndGetId() {
        // GIVEN
        String id = "123";
        // WHEN
        info.setId(id);
        // THEN
        assertThat(info.getId()).isEqualTo(id);
    }

    @Test
    void testSetAndGetTitle() {
        // GIVEN
        String title = "Sample Title";
        // WHEN
        info.setTitle(title);
        // THEN
        assertThat(info.getTitle()).isEqualTo(title);
    }

    @Test
    void testSetAndGetDescription() {
        // GIVEN
        String description = "Sample Description";
        // WHEN
        info.setDescription(description);
        // THEN
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    void testSetIdToNull() {
        // GIVEN
        // WHEN
        info.setId(null);
        // THEN
        assertThat(info.getId()).isNull();
    }

    @Test
    void testSetTitleToNull() {
        // GIVEN
        // WHEN
        info.setTitle(null);
        // THEN
        assertThat(info.getTitle()).isNull();
    }

    @Test
    void testSetDescriptionToNull() {
        // GIVEN
        // WHEN
        info.setDescription(null);
        // THEN
        assertThat(info.getDescription()).isNull();
    }

    @Test
    void testMultipleSetOperations() {
        // GIVEN
        String id = "456";
        String title = "Another Title";
        String description = "Another Description";
        // WHEN
        info.setId(id);
        info.setTitle(title);
        info.setDescription(description);
        // THEN
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    void testSetIdEmptyString() {
        // GIVEN
        // WHEN
        info.setId("");
        // THEN
        assertThat(info.getId()).isEmpty();
    }

    @Test
    void testSetTitleEmptyString() {
        // GIVEN
        // WHEN
        info.setTitle("");
        // THEN
        assertThat(info.getTitle()).isEmpty();
    }

    @Test
    void testSetDescriptionEmptyString() {
        // GIVEN
        // WHEN
        info.setDescription("");
        // THEN
        assertThat(info.getDescription()).isEmpty();
    }

    @Test
    void testOnPrePersistSetsCreatedAt() {
        // GIVEN
        Info newInfo = new Info();
        // WHEN
        newInfo.onPrePersist();
        // THEN
        assertThat(newInfo.getCreatedAt()).isNotNull();
        assertThat(newInfo.getCreatedAt()).isCloseTo(new Date(), 1000);
    }

    @Test
    void testCreatedAtIsNullBeforePrePersist() {
        // GIVEN
        // WHEN
        // THEN
        assertThat(info.getCreatedAt()).isNull();
    }
}
