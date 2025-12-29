package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org. assertj. core. api. Assertions. assertThat;
import static org. assertj. core. api. Assertions. assertThatThrownBy;

import org. junit. jupiter. api. AfterEach;
import org. junit. jupiter. api. AfterAll;
import org. junit. jupiter. api. BeforeEach;
import org. junit. jupiter. api. BeforeAll;
import org. junit. jupiter. api. Test;
import org. junit. jupiter. api. extension. ExtendWith;
import org. mockito. Mock;
import org. mockito. Mockito;
import org. mockito. junit. jupiter. MockitoExtension;

import static org. junit. jupiter. api. Assertions. assertEquals;
import static org. junit. jupiter. api. Assertions. assertThrows;

public class UserRequestGeneratedAiTests {

    @Test
    public void testUserRequestCreation() {
        // Test creating a UserRequest object
        UserRequest userRequest = new UserRequest();
        userRequest. setName("John Doe");
        userRequest. setEmail("john. do. example. com");

        // Verify the values are set correctly
        assertEquals("John Doe", userRequest. getName());
        assertEquals("john. do. example. com", userRequest. getEmail());
    }

    @Test
    public void testUserRequestWithConstructor() {
        // Test creating a UserRequest with constructor
        UserRequest userRequest = new UserRequest("Jane Doe", "jane. do. example. com");

        // Verify the values are set correctly
        assertEquals("Jane Doe", userRequest. getName());
        assertEquals("jane. do. example. com", userRequest. getEmail());
    }
}

package com. bestpractice. api. domain. model;

public class UserRequest {
    private String name;
    private String email;

    // Default constructor
    public UserRequest() {
    }

    // Constructor with parameters
    public UserRequest(String name, String email) {
        this. name = name;
        this. email = email;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this. name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this. email = email;
    }
}
