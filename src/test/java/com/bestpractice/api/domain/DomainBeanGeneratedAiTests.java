package com.bestpractice.api.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DomainBeanGeneratedAiTests {

    private DomainBean domainBean;

    @BeforeEach
    void setUp() {
        // GIVEN a fresh instance of DomainBean
        domainBean = new DomainBean();
    }

    @Test
    void testPasswordEncoderReturnsBCryptPasswordEncoder() {
        // GIVEN
        // (no additional setup needed)

        // WHEN
        PasswordEncoder encoder = domainBean.passwordEncoder();

        // THEN
        assertTrue(encoder instanceof BCryptPasswordEncoder,
                "The returned PasswordEncoder should be an instance of BCryptPasswordEncoder");
    }

    @Test
    void testPasswordEncoderCanEncodeAndMatchesPassword() {
        // GIVEN
        String rawPassword = "SecurePassword123!";
        PasswordEncoder encoder = domainBean.passwordEncoder();

        // WHEN
        String encodedPassword = encoder.encode(rawPassword);

        // THEN
        assertTrue(encoder.matches(rawPassword, encodedPassword),
                "The encoded password should match the raw password");
    }

    @Test
    void testPasswordEncoderReturnsNewInstanceEachCall() {
        // GIVEN
        // (no additional setup needed)

        // WHEN
        PasswordEncoder firstEncoder = domainBean.passwordEncoder();
        PasswordEncoder secondEncoder = domainBean.passwordEncoder();

        // THEN
        assertEquals(false, firstEncoder == secondEncoder,
                "Each call to passwordEncoder() should return a new instance");
    }

    @Test
    void testPasswordEncoderEncodeNullThrowsException() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class,
                () -> encoder.encode(null),
                "Encoding a null password should throw IllegalArgumentException");
    }

    @Test
    void testPasswordEncoderMatchesNullThrowsException() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class,
                () -> encoder.matches(null, "anyEncoded"),
                "Matching with a null raw password should throw IllegalArgumentException");

        assertThrows(IllegalArgumentException.class,
                () -> encoder.matches("anyRaw", null),
                "Matching with a null encoded password should throw IllegalArgumentException");
    }
}
