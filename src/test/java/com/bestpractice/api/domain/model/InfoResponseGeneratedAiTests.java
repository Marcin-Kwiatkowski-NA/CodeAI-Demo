package com.bestpractice.api.domain.model;

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
import static org.assertj.core.api.Assertions.assertThat;

public class InfoResponseGeneratedAiTests {

    private InfoResponse infoResponse;

    @BeforeEach
    void setUp() {
        infoResponse = new InfoResponse("123", "Test Title", "Test Description");
    }

    @Test
    void testGettersReturnCorrectValues() {
        // GIVEN
        // infoResponse initialized in setUp

        // WHEN
        String id = infoResponse.getId();
        String title = infoResponse.getTitle();
        String description = infoResponse.getDescription();

        // THEN
        assertThat(id).isEqualTo("123");
        assertThat(title).isEqualTo("Test Title");
        assertThat(description).isEqualTo("Test Description");
    }

    @Test
    void testConstructorWithNullValues() {
        // GIVEN
        // null values

        // WHEN
        InfoResponse nullInfo = new InfoResponse(null, null, null);

        // THEN
        assertThat(nullInfo.getId()).isNull();
        assertThat(nullInfo.getTitle()).isNull();
        assertThat(nullInfo.getDescription()).isNull();
    }

    @Test
    void testConstructorWithEmptyStrings() {
        // GIVEN
        // empty string values

        // WHEN
        InfoResponse emptyInfo = new InfoResponse("", "", "");

        // THEN
        assertThat(emptyInfo.getId()).isEmpty();
        assertThat(emptyInfo.getTitle()).isEmpty();
        assertThat(emptyInfo.getDescription()).isEmpty();
    }

    @Test
    void testConstructorWithNonNullValues() {
        // GIVEN
        String id = "456";
        String title = "Another Title";
        String description = "Another Description";

        // WHEN
        InfoResponse nonNullInfo = new InfoResponse(id, title, description);

        // THEN
        assertThat(nonNullInfo.getId()).isEqualTo(id);
        assertThat(nonNullInfo.getTitle()).isEqualTo(title);
        assertThat(nonNullInfo.getDescription()).isEqualTo(description);
    }
}
