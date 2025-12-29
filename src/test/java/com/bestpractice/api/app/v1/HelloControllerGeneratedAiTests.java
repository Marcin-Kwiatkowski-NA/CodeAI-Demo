package com.bestpractice.api.app.v1;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class HelloControllerGeneratedAiTests {

    @InjectMocks
    private HelloController helloController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(helloController).build();
    }

    @Test
    void sample1_ShouldReturnHelloWorld() {
        // GIVEN: A request to the /api/v1/hello endpoint
        // WHEN: The endpoint is called
        // THEN: The response should contain the expected message
        Map<String, String> result = helloController.sample1();
        assertThat(result).containsEntry("key", "Hello world.");
    }

    @Test
    void sample1_ShouldReturnCorrectStatusAndBody() throws Exception {
        // GIVEN: A request to the /api/v1/hello endpoint
        // WHEN: The endpoint is called
        // THEN: The response should have status 200 and the correct body
        mockMvc.perform(get("/api/v1/hello"))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String content = result.getResponse().getContentAsString();
                    assertThat(content).contains("\"key\":\"Hello world.\"");
                });
    }
}
