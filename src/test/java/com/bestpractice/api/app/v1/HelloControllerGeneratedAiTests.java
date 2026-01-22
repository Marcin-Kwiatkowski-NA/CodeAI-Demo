package com.bestpractice.api.app.v1;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest(HelloController.class)
@ExtendWith(SpringExtension.class)
public class HelloControllerGeneratedAiTests {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        // No state to reset
    }

    @Test
    void sample1ReturnsCorrectMap() {
        // GIVEN
        HelloController controller = new HelloController();

        // WHEN
        Map<String, String> result = controller.sample1();

        // THEN
        assertThat(result).isNotNull();
        assertThat(result).containsEntry("key", "Hello world.");
    }

    @Test
    void getHelloEndpointReturnsExpectedResponse() throws Exception {
        // GIVEN
        // MockMvc is autowired

        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/hello"))
                // THEN
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.key").value("Hello world."));
    }
}
