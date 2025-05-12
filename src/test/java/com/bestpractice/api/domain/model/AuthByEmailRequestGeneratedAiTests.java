package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;

public class AuthByEmailRequest {

    @Email
    @NotNull
    @JsonProperty("email")
    private String email;

    @NotNull
    @JsonProperty("password")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @BeforeEach
    public void setUp() {
        // Reset the state of the test before each test
    }

    @Test
    public void testGetEmailReturnsCorrectValue() {
        // Arrange
        String email = "test@example.com";
        String password = "password123";

        // Act
        assertEquals(email, email);
        assertEquals("test@example.com", email);

        // Assert
        assertEquals(password, password);
    }

    @Test
    public void testGetPasswordReturnsCorrectValue() {
        // Arrange
        String email = "test@example.com";
        String password = "password123";

        // Act
        assertEquals(password, password);

        // Assert
        assertEquals(password, password);
    }

    @Test
    public void testSetPasswordReturnsCorrectValue() {
        // Arrange
        String email = "test@example.com";
        String password = "password123";

        // Act
        setPassword(password);

        // Assert
        assertEquals(password, password);
    }
}
