package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
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

    // Test for getId and setId methods
    @Test
    void givenId_whenSetId_thenGetIdReturnsSameValue() {
        // GIVEN
        String expectedId = "123";

        // WHEN
        info.setId(expectedId);

        // THEN
        assertThat(info.getId()).isEqualTo(expectedId);
    }

    // Test for getTitle and setTitle methods
    @Test
    void givenTitle_whenSetTitle_thenGetTitleReturnsSameValue() {
        // GIVEN
        String expectedTitle = "Test Title";

        // WHEN
        info.setTitle(expectedTitle);

        // THEN
        assertThat(info.getTitle()).isEqualTo(expectedTitle);
    }

    // Test for getDescription and setDescription methods
    @Test
    void givenDescription_whenSetDescription_thenGetDescriptionReturnsSameValue() {
        // GIVEN
        String expectedDescription = "Test Description";

        // WHEN
        info.setDescription(expectedDescription);

        // THEN
        assertThat(info.getDescription()).isEqualTo(expectedDescription);
    }

    // Test for inherited getCreatedAt and setCreatedAt methods
    @Test
    void givenCreatedAt_whenSetCreatedAt_thenGetCreatedAtReturnsSameValue() {
        // GIVEN
        Date expectedDate = new Date();

        // WHEN
        info.setCreatedAt(expectedDate);

        // THEN
        assertThat(info.getCreatedAt()).isEqualTo(expectedDate);
    }
}
