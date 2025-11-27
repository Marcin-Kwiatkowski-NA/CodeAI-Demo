package com.bestpractice.api.domain.model;

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
import static org.assertj.core.api.Assertions.assertThat;

public class AuthByEmailRequestGeneratedAiTests {

    private AuthByEmailRequest authByEmailRequest;

    @BeforeEach
    public void setUp() {
        authByEmailRequest = new AuthByEmailRequest();
    }

    @Test
    public void givenValidEmail_whenSetEmail_thenEmailShouldBeSetCorrectly() {
        // GIVEN
        String email = "test@example.com";

        // WHEN
        authByEmailRequest.setEmail(email);

        // THEN
        assertThat(authByEmailRequest.getEmail()).isEqualTo(email);
    }

    @Test
    public void givenValidPassword_whenSetPassword_thenPasswordShouldBeSetCorrectly() {
        // GIVEN
        String password = "securePassword123";

        // WHEN
        authByEmailRequest.setPassword(password);

        // THEN
        assertThat(authByEmailRequest.getPassword()).isEqualTo(password);
    }

    @Test
    public void givenNoEmail_whenGetEmail_thenShouldReturnNull() {
        // GIVEN
        // No email is set

        // WHEN
        String email = authByEmailRequest.getEmail();

        // THEN
        assertThat(email).isNull();
    }

    @Test
    public void givenNoPassword_whenGetPassword_thenShouldReturnNull() {
        // GIVEN
        // No password is set

        // WHEN
        String password = authByEmailRequest.getPassword();

        // THEN
        assertThat(password).isNull();
    }
}
