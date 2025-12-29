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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class HelloControllerGeneratedAiTests {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        // Reset any state or configuration that might affect test outcomes
        // This ensures each test starts with a clean environment
    }

    @Test
    void givenValidRequest_whenGetHello_thenReturnsHelloWorld() throws Exception {
        // GIVEN: A valid request to the /api/v1/hello endpoint
        // WHEN: The GET request is made to /api/v1/hello
        // THEN: The response should return a JSON with key "key" and value "Hello world."
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/hello")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"key\":\"Hello world.\"}"));
    }
}
