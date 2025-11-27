package com.bestpractice.api.app.v1;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerGeneratedAiTests {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        // No additional setup required as @SpringBootTest and @AutoConfigureMockMvc initialize the MockMvc instance
    }

    @Test
    void givenValidRequest_whenGetHelloEndpoint_thenReturnsHelloWorld() throws Exception {
        // GIVEN: A valid request to the /hello endpoint

        // WHEN: The endpoint is called
        var result = mockMvc.perform(get("/api/v1/hello"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.key").value("Hello world."))
                .andReturn();

        // THEN: The response contains the expected key-value pair
        var responseContent = result.getResponse().getContentAsString();
        assertThat(responseContent).contains("\"key\":\"Hello world.\"");
    }
}

package com.bestpractice.api.app.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class HelloController {

    @ResponseBody
    @GetMapping(value = "/hello")
    public Map<String, String> sample1() {
        return Collections.singletonMap("key", "Hello world.");
    }
}
