package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javax.validation.constraints.NotNull;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InfoGeneratedAiTests {

    private ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Test
    public void givenValidData_whenCreateInfo_thenAllFieldsAreSetCorrectly() {
        // GIVEN
        String validId = "id-123";
        String validTitle = "Test Title";
        String validDescription = "Test Description";

        // WHEN
        Info info = new Info();
        info.setId(validId);
        info.setTitle(validTitle);
        info.setDescription(validDescription);

        // THEN
        assertThat(info.getId()).isEqualTo(validId);
        assertThat(info.getTitle()).isEqualTo(validTitle);
        assertThat(info.getDescription()).isEqualTo(validDescription);
        assertThat(info.getCreatedAt()).isNotNull();
    }

    @Test
    public void givenNullTitle_whenSetTitle_thenValidationExceptionIsThrown() {
        // GIVEN
        Info info = new Info();
        info.setId("id-123");

        // WHEN & THEN
        assertThatThrownBy(() -> {
            info.setTitle(null);
        }).isInstanceOf(java.lang.IllegalArgumentException.class)
          .hasMessageContaining("title");
    }

    @Test
    public void givenNullDescription_whenSetDescription_thenValidationExceptionIsThrown() {
        // GIVEN
        Info info = new Info();
        info.setId("id-123");

        // WHEN & THEN
        assertThatThrownBy(() -> {
            info.setDescription(null);
        }).isInstanceOf(java.lang.IllegalArgumentException.class)
          .hasMessageContaining("description");
    }

    @Test
    public void givenValidInfo_whenToJsonString_thenJsonIsGeneratedCorrectly() {
        // GIVEN
        Info info = new Info();
        info.setId("id-123");
        info.setTitle("Test Title");
        info.setDescription("Test Description");

        // WHEN
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(info);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize Info to JSON", e);
        }

        // THEN
        assertThat(jsonString).isNotNull();
        assertThat(jsonString).contains("\"id\":\"id-123\"");
        assertThat(jsonString).contains("\"title\":\"Test Title\"");
        assertThat(jsonString).contains("\"description\":\"Test Description\"");
        assertThat(jsonString).contains("\"created_at");
    }

    @Test
    public void givenInfoWithEmptyFields_whenToJsonString_thenCreatedAtIsPresent() {
        // GIVEN
        Info info = new Info();
        info.setId("id-127");
        info.setTitle("Test Title");
        info.setDescription("Test Description");

        // WHEN
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(info);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize Info to JSON", e);
        }

        // THEN
        assertThat(jsonString).isNotNull();
        assertThat(jsonString).contains("\"created_at");
    }

    @Test
    public void givenInfoWithNullValues_whenCreateInfo_thenFieldsAreSetToNull() {
        // GIVEN
        Info info = new Info();
        info.setId(null);
        info.setTitle(null);
        info.setDescription(null);

        // THEN
        assertThat(info.getId()).isNull();
        assertThat(info.getTitle()).isNull();
        assertThat(info.getDescription()).isNull();
    }
}
