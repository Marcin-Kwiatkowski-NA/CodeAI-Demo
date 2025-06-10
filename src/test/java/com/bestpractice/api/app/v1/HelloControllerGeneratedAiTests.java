package com.bestpractice.api.app.v1;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HelloController.class)
public class HelloControllerGeneratedAiTests {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        // Reset any state if needed before each test
    }

    @Test
    public void givenValidRequest_whenGetHello_thenReturnHelloWorld() throws Exception {
        // GIVEN: No specific setup required for this test

        // WHEN: Perform the GET request to /api/v1/hello
        mockMvc.perform(get("/api/v1/hello")
                .accept(MediaType.APPLICATION_JSON))

                // THEN: Verify the response status and content
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.key").value("Hello world."));
    }
}
