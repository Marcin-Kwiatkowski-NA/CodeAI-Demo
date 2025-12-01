package com.bestpractice.api.app.v1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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
        mockMvc.perform(get("/api/v1/hello")
                .accept(MediaType.APPLICATION_JSON))
                // THEN: The response status is 200 OK and the body contains the expected JSON
                .andExpect(status().isOk())
                .andExpect(content().json("{\"key\":\"Hello world.\"}"));
    }
}
