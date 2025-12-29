package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

import com.bestpractice.api.infrastrucuture.entity.Info;

public class InfoRequestGeneratedAiTests {

    @Test
    public void givenValidTitleAndDescription_whenConvertIsCalled_thenInfoIsCreatedWithCorrectValues() {
        // GIVEN
        String id = "test-id-123";
        String title = "Test Title";
        String description = "Test Description";

        // WHEN
        Info result = new InfoRequest().convert(id, title, description);

        // THEN
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getTitle()).isEqualTo(title);
        assertThat(result.getDescription()).isEqualTo(description);
    }

    @Test
    public void givenNullTitle_whenConvertIsCalled_thenShouldThrowException() {
        // GIVEN
        String id = "test-id-123";

        // WHEN & THEN
        assertThatThrownBy(() -> new InfoRequest().convert(id, null, "Test Description"))
                .isInstanceOf(java.lang.IllegalArgumentException.class)
                .hasMessageContaining("title");
    }

    @Test
    public void givenNullDescription_whenConvertIsCalled_thenShouldThrowException() {
        // GIVEN
        String id = "test-id-123";

        // WHEN & THEN
        assertThatThrownBy(() -> new InfoRequest().convert(id, "Test Title", null))
                .isInstanceOf(java.lang.IllegalArgumentException.class)
                .hasMessageContaining("description");
    }

    @Test
    public void givenEmptyTitle_whenConvertIsCalled_thenShouldThrowException() {
        // GIVEN
        String id = "test-id-123";

        // WHEN & THEN
        assertThatThrownBy(() -> new InfoRequest().convert(id, "", "Test Description"))
                .isInstanceOf(java.lang.IllegalArgumentException.class)
                .hasMessageContaining("title");
    }

    @Test
    public void givenEmptyDescription_whenConvertIsCalled_thenShouldThrowException() {
        // GIVEN
        String id = "test-id-123";

        // WHEN & THEN
        assertThatThrownBy(() -> new InfoRequest().convert(id, "Test Title", ""))
                .isInstanceOf(java.lang.IllegalArgumentException.class)
                .hasMessageContaining("description");
    }

    @Test
    public void givenValidInputs_whenConvertIsCalled_thenCreatedAtIsSetInResult() {
        // GIVEN
        String id = "test-id-123";
        String title = "Test Title";
        String description = "Test Description";

        // WHEN
        Info result = new InfoRequest().convert(id, title, description);

        // THEN
        assertThat(result.getCreatedAt()).isNotNull();
    }
}
