package com.bestpractice.api.domain.model;

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
import static org.assertj.core.api.Assertions.assertThat;

public class InfoResponseGeneratedAiTests {

    private InfoResponse infoResponse;

    @BeforeEach
    void setUp() {
        // GIVEN
        infoResponse = new InfoResponse("123", "Test Title", "Test Description");
    }

    @Test
    void testGetId() {
        // GIVEN
        // WHEN
        String id = infoResponse.getId();
        // THEN
        assertThat(id).isEqualTo("123");
    }

    @Test
    void testGetTitle() {
        // GIVEN
        // WHEN
        String title = infoResponse.getTitle();
        // THEN
        assertThat(title).isEqualTo("Test Title");
    }

    @Test
    void testGetDescription() {
        // GIVEN
        // WHEN
        String description = infoResponse.getDescription();
        // THEN
        assertThat(description).isEqualTo("Test Description");
    }

    @Test
    void testConstructorSetsFields() {
        // GIVEN
        // WHEN
        InfoResponse ir = new InfoResponse("abc", "Another Title", "Another Description");
        // THEN
        assertThat(ir.getId()).isEqualTo("abc");
        assertThat(ir.getTitle()).isEqualTo("Another Title");
        assertThat(ir.getDescription()).isEqualTo("Another Description");
    }

    @Test
    void testConstructorWithNullValues() {
        // GIVEN
        // WHEN
        InfoResponse ir = new InfoResponse(null, null, null);
        // THEN
        assertThat(ir.getId()).isNull();
        assertThat(ir.getTitle()).isNull();
        assertThat(ir.getDescription()).isNull();
    }
}
