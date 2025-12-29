package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InfoResponseGeneratedAiTests {

    @Mock
    private ObjectMapper objectMapper;

    @Test
    void givenValidIdTitleDescription_whenCreateInfoResponse_thenAllFieldsAreSet() {
        // GIVEN
        String expectedId = "id-123";
        String expectedTitle = "Sample Title";
        String expectedDescription = "This is a sample description.";

        // WHEN
        InfoResponse response = new InfoResponse(expectedId, expectedTitle, expectedDescription);

        // THEN
        assertThat(response.getId()).isEqualTo(expectedId);
        assertThat(response.getTitle()).isEqualTo(expectedTitle);
        assertThat(response.getDescription()).isEqualTo(expectedDescription);
    }

    @Test
    void givenNullId_whenCreateInfoResponse_thenThrowException() {
        // WHEN & THEN
        assertThatThrownBy(() -> new InfoResponse(null, "Sample Title", "This is a sample description."))
                .hasMessageContaining("id");
    }

    @Test
    void givenNullTitle_whenCreateInfoResponse_thenThrowException() {
        // WHEN & THEN
        assertThatThrownBy(() -> new InfoResponse("id-123", null, "This is a sample description."))
                .hasMessageContaining("title");
    }

    @Test
    void givenNullDescription_whenCreateInfoResponse_thenThrowException() {
        // WHEN & THEN
        assertThatThrownBy(() -> new InfoResponse("id-123", "Sample Title", null))
                .hasMessageContaining("description");
    }

    @Test
    void givenValidResponse_whenSerialize_thenJsonIsProperlyFormatted() {
        // GIVEN
        String expectedJson = "{\n" +
                "  \"id\" : \"id-123\",\n" +
                "  \"title\" : \"Sample Title\",\n" +
                "  \"description\" : \"This is a sample description.\"\n" +
                "}";

        // WHEN
        String json = null;
        try {
            json = objectMapper.writeValueAsString(new InfoResponse("id-123", "Sample Title", "This is a sample description."));
        } catch (JsonProcessingException e) {
            json = "Error during serialization";
        }

        // THEN
        assertThat(json).isEqualTo(expectedJson);
    }
}
