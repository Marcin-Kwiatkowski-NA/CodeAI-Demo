package com.bestpractice.api.app.v1;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HelloController.class)
@ExtendWith(MockitoExtension.class)
class HelloControllerGeneratedAiTests {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        // No additional setup required as @WebMvcTest initializes the MockMvc instance
    }

    @Test
    void testSample1Endpoint() throws Exception {
        // GIVEN: The endpoint is set up and ready to be called

        // WHEN: A GET request is made to the /api/v1/hello endpoint
        var result = mockMvc.perform(get("/api/v1/hello"))
                .andExpect(status().isOk())
                .andReturn();

        // THEN: The response should contain the expected key-value pair
        String responseContent = result.getResponse().getContentAsString();
        String expectedResponse = "{\"key\":\"Hello world.\"}";
        assertThat(responseContent).isEqualTo(expectedResponse);
    }
}
