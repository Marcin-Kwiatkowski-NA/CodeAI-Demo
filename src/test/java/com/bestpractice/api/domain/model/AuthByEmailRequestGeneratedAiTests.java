package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AuthByEmailRequestGeneratedAiTests {

    private AuthByEmailRequest request;

    @BeforeEach
    void setUp() {
        request = new AuthByEmailRequest();
    }

    @Test
    void testSetAndGetEmail() {
        // GIVEN
        String email = "user@example.com";

        // WHEN
        request.setEmail(email);

        // THEN
        assertThat(request.getEmail()).isEqualTo(email);
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN
        String password = "secret";

        // WHEN
        request.setPassword(password);

        // THEN
        assertThat(request.getPassword()).isEqualTo(password);
    }

    @Test
    void testSetBothFieldsAndGetters() {
        // GIVEN
        String email = "user@example.com";
        String password = "secret";

        // WHEN
        request.setEmail(email);
        request.setPassword(password);

        // THEN
        assertThat(request.getEmail()).isEqualTo(email);
        assertThat(request.getPassword()).isEqualTo(password);
    }

    @Test
    void testEmailCanBeNull() {
        // GIVEN
        String email = null;

        // WHEN
        request.setEmail(email);

        // THEN
        assertThat(request.getEmail()).isNull();
    }

    @Test
    void testPasswordCanBeNull() {
        // GIVEN
        String password = null;

        // WHEN
        request.setPassword(password);

        // THEN
        assertThat(request.getPassword()).isNull();
    }

    @Test
    void testDefaultState() {
        // GIVEN nothing

        // WHEN nothing

        // THEN
        assertThat(request.getEmail()).isNull();
        assertThat(request.getPassword()).isNull();
    }

    @Test
    void testEmailIndependentFromPassword() {
        // GIVEN
        String email = "user@example.com";
        String password = "secret";

        // WHEN
        request.setEmail(email);
        request.setPassword(password);
        request.setEmail(null);

        // THEN
        assertThat(request.getEmail()).isNull();
        assertThat(request.getPassword()).isEqualTo(password);
    }

    @Test
    void testPasswordIndependentFromEmail() {
        // GIVEN
        String email = "user@example.com";
        String password = "secret";

        // WHEN
        request.setEmail(email);
        request.setPassword(password);
        request.setPassword(null);

        // THEN
        assertThat(request.getPassword()).isNull();
        assertThat(request.getEmail()).isEqualTo(email);
    }
}
