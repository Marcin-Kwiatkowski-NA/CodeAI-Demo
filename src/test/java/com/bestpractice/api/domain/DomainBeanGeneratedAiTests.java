package com.bestpractice.api.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class DomainBeanGeneratedAiTests {

    private DomainBean domainBean;

    @BeforeEach
    void setUp() {
        domainBean = new DomainBean();
    }

    @Test
    void givenDomainBean_whenPasswordEncoderCalled_thenReturnNonNullBCryptPasswordEncoder() {
        // GIVEN
        // DomainBean instance is initialized in setUp()

        // WHEN
        PasswordEncoder encoder = domainBean.passwordEncoder();

        // THEN
        assertNotNull(encoder);
        assertTrue(encoder instanceof BCryptPasswordEncoder);
    }

    @Test
    void givenPasswordEncoder_whenEncodeCalledWithValidInput_thenReturnEncodedString() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "testPassword";

        // WHEN
        String encodedPassword = encoder.encode(rawPassword);

        // THEN
        assertNotNull(encodedPassword);
        assertTrue(encodedPassword.length() > 0);
        assertTrue(encoder.matches(rawPassword, encodedPassword));
    }

    @Test
    void givenPasswordEncoder_whenEncodeCalledWithNull_thenThrowException() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> encoder.encode(null));
    }

    @Test
    void givenPasswordEncoder_whenMatchesCalledWithInvalidInput_thenReturnFalse() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "password";
        String encodedPassword = encoder.encode(rawPassword);

        // WHEN
        boolean result = encoder.matches("wrongPassword", encodedPassword);

        // THEN
        assertEquals(false, result);
    }

    @Test
    void givenPasswordEncoder_whenMatchesCalledWithNullEncodedPassword_thenThrowException() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "password";

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> encoder.matches(rawPassword, null));
    }

    @Test
    void givenPasswordEncoder_whenMatchesCalledWithNullRawPassword_thenThrowException() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String encodedPassword = encoder.encode("password");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> encoder.matches(null, encodedPassword));
    }
}
