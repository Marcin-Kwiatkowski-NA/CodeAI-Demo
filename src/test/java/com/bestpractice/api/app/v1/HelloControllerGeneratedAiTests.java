package com.bestpractice.api.app.v1;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
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
    void sample1_shouldReturnHelloWorld() throws Exception {
        // GIVEN: A request to the /api/v1/hello endpoint

        // WHEN: The request is performed
        mockMvc.perform(get("/api/v1/hello")
                .accept(MediaType.APPLICATION_JSON))
                // THEN: The response should contain the expected key-value pair
                .andExpect(status().isOk())
                .andExpect(content().json("{\"key\":\"Hello world.\"}"));
    }

    @Test
    void sample1_shouldHandleInvalidEndpoint() throws Exception {
        // GIVEN: A request to an invalid endpoint

        // WHEN: The request is performed
        mockMvc.perform(get("/api/v1/invalid")
                .accept(MediaType.APPLICATION_JSON))
                // THEN: The response should return a 404 Not Found status
                .andExpect(status().isNotFound());
    }

    @Test
    void sample1_shouldHandleUnsupportedMediaType() throws Exception {
        // GIVEN: A request with an unsupported media type

        // WHEN: The request is performed
        mockMvc.perform(get("/api/v1/hello")
                .accept(MediaType.APPLICATION_XML))
                // THEN: The response should return a 406 Not Acceptable status
                .andExpect(status().isNotAcceptable());
    }

    @Test
    void sample1_shouldVerifyResponseContent() throws Exception {
        // GIVEN: A request to the /api/v1/hello endpoint

        // WHEN: The request is performed
        String responseContent = mockMvc.perform(get("/api/v1/hello")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // THEN: The response content should match the expected value
        assertEquals("{\"key\":\"Hello world.\"}", responseContent);
    }

    @Test
    void sample1_shouldNotThrowExceptionForValidRequest() throws Exception {
        // GIVEN: A valid request to the /api/v1/hello endpoint

        // WHEN: The request is performed
        mockMvc.perform(get("/api/v1/hello")
                .accept(MediaType.APPLICATION_JSON))
                // THEN: No exception should be thrown and the response should be valid
                .andExpect(status().isOk())
                .andExpect(content().json("{\"key\":\"Hello world.\"}"));
    }
}
