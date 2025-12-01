package com.bestpractice.api.app.v1;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class HelloControllerGeneratedAiTests {

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        HelloController helloController = new HelloController();
        mockMvc = MockMvcBuilders.standaloneSetup(helloController).build();
    }

    @Test
    void givenRequestToHelloEndpoint_whenGetRequestIsMade_thenResponseContainsHelloWorld() throws Exception {
        // GIVEN: A setup with the HelloController and MockMvc

        // WHEN: A GET request is made to the /api/v1/hello endpoint
        var result = mockMvc.perform(get("/api/v1/hello")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        // THEN: The response contains the expected key-value pair
        String responseContent = result.getResponse().getContentAsString();
        assertThat(responseContent).contains("\"key\":\"Hello world.\"");
    }
}
