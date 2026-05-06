package com.bestpractice.api.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;
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
    void passwordEncoder_ShouldReturnBCryptPasswordEncoderInstance() {
        // GIVEN
        // DomainBean instance is initialized in setUp()

        // WHEN
        PasswordEncoder encoder = domainBean.passwordEncoder();

        // THEN
        assertNotNull(encoder, "PasswordEncoder bean should not be null");
        assertEquals(BCryptPasswordEncoder.class, encoder.getClass(), "PasswordEncoder should be an instance of BCryptPasswordEncoder");
    }

    @Test
    void passwordEncoder_ShouldEncodeAndMatchPasswordCorrectly() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "securePassword123"; // security-sensitive: password handling

        // WHEN
        String encodedPassword = encoder.encode(rawPassword);
        boolean matches = encoder.matches(rawPassword, encodedPassword);

        // THEN
        assertNotNull(encodedPassword, "Encoded password should not be null");
        assertTrue(matches, "Encoded password should match the raw password");
    }

    @Test
    void passwordEncoder_ShouldThrowException_WhenNullPasswordProvided() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> encoder.encode(null), "Encoding null password should throw IllegalArgumentException");
    }

    @Test
    void passwordEncoder_ShouldNotMatchDifferentPasswords() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "securePassword123"; // security-sensitive: password handling
        String differentPassword = "differentPassword456"; // security-sensitive: password handling

        // WHEN
        String encodedPassword = encoder.encode(rawPassword);
        boolean matches = encoder.matches(differentPassword, encodedPassword);

        // THEN
        assertTrue(!matches, "Encoded password should not match a different raw password");
    }
}
