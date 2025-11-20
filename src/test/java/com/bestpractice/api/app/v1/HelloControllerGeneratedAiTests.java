package com.bestpractice.api.app.v1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class HelloControllerGeneratedAiTests {

    private MockMvc mockMvc;

    private HelloController helloController;

    @BeforeEach
    void setUp() {
        helloController = new HelloController();
        mockMvc = MockMvcBuilders.standaloneSetup(helloController).build();
    }

    @Test
    void givenHelloEndpoint_whenGetRequest_thenReturnsHelloWorld() throws Exception {
        // GIVEN
        String expectedResponse = "{\"key\":\"Hello world.\"}";

        // WHEN
        mockMvc.perform(get("/api/v1/hello")
                .contentType(MediaType.APPLICATION_JSON))
                // THEN
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @Test
    void givenHelloEndpoint_whenGetRequest_thenResponseIsNotNull() throws Exception {
        // GIVEN
        String expectedResponse = "{\"key\":\"Hello world.\"}";

        // WHEN
        String actualResponse = mockMvc.perform(get("/api/v1/hello")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        // THEN
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void givenInvalidEndpoint_whenGetRequest_thenReturnsNotFound() throws Exception {
        // GIVEN
        String invalidEndpoint = "/api/v1/invalid";

        // WHEN
        mockMvc.perform(get(invalidEndpoint)
                .contentType(MediaType.APPLICATION_JSON))
                // THEN
                .andExpect(status().isNotFound());
    }

    @Test
    void givenHelloEndpoint_whenGetRequest_thenNoExceptionThrown() throws Exception {
        // GIVEN
        String validEndpoint = "/api/v1/hello";

        // WHEN
        mockMvc.perform(get(validEndpoint)
                .contentType(MediaType.APPLICATION_JSON))
                // THEN
                .andExpect(status().isOk());
    }

    @Test
    void givenInvalidEndpoint_whenGetRequest_thenThrowsException() throws Exception {
        // GIVEN
        String invalidEndpoint = "/api/v1/invalid";

        // WHEN
        mockMvc.perform(get(invalidEndpoint)
                .contentType(MediaType.APPLICATION_JSON))
                // THEN
                .andExpect(status().isNotFound());
    }
}
