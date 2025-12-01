package com.bestpractice.api.app.v1;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class HelloControllerGeneratedAiTests {

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        HelloController helloController = new HelloController();
        mockMvc = MockMvcBuilders.standaloneSetup(helloController).build();
    }

    @Test
    void givenRequestToHelloEndpoint_whenGetRequestIsMade_thenReturnsHelloWorldResponse() throws Exception {
        // GIVEN: A properly configured MockMvc instance and a valid endpoint

        // WHEN: A GET request is made to the /api/v1/hello endpoint
        String responseContent = mockMvc.perform(get("/api/v1/hello")
                .contentType(MediaType.APPLICATION_JSON))
                // THEN: The response status is 200 OK and the body contains the expected JSON
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals("{\"key\":\"Hello world.\"}", responseContent);
    }

    @Test
    void givenInvalidEndpoint_whenGetRequestIsMade_thenReturns404NotFound() throws Exception {
        // GIVEN: A properly configured MockMvc instance and an invalid endpoint

        // WHEN: A GET request is made to an invalid endpoint
        mockMvc.perform(get("/api/v1/invalid")
                .contentType(MediaType.APPLICATION_JSON))
                // THEN: The response status is 404 Not Found
                .andExpect(status().isNotFound());
    }

    @Test
    void givenRequestToHelloEndpoint_whenExceptionOccurs_thenHandleGracefully() throws Exception {
        // GIVEN: A properly configured MockMvc instance and a valid endpoint
        HelloController helloController = new HelloController();
        MockMvc mockMvcWithException = MockMvcBuilders.standaloneSetup(helloController).build();

        // WHEN: A GET request is made to the /api/v1/hello endpoint
        // THEN: No exception is thrown, and the response is handled gracefully
        String responseContent = mockMvcWithException.perform(get("/api/v1/hello")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals("{\"key\":\"Hello world.\"}", responseContent);
    }
}
