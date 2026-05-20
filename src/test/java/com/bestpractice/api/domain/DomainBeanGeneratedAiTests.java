package com.bestpractice.api.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.junit.jupiter.api.Assertions;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DomainBeanGeneratedAiTests {

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
        Assertions.assertNotNull(encoder, "PasswordEncoder bean should not be null");
        Assertions.assertEquals(BCryptPasswordEncoder.class, encoder.getClass(), "PasswordEncoder should be an instance of BCryptPasswordEncoder");
    }

    @Test
    void passwordEncoder_ShouldEncodePasswordCorrectly() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "securePassword123"; // security-sensitive test data

        // WHEN
        String encodedPassword = encoder.encode(rawPassword);

        // THEN
        Assertions.assertNotNull(encodedPassword, "Encoded password should not be null");
        Assertions.assertNotEquals(rawPassword, encodedPassword, "Encoded password should differ from raw password");
        Assertions.assertTrue(encoder.matches(rawPassword, encodedPassword), "Encoded password should match raw password when verified");
    }

    @Test
    void passwordEncoder_ShouldThrowException_WhenNullPasswordProvided() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = null;

        // WHEN & THEN
        Assertions.assertThrows(IllegalArgumentException.class, () -> encoder.encode(rawPassword), "Encoding null password should throw IllegalArgumentException");
    }

    @Test
    void passwordEncoder_ShouldProduceDifferentHashesForSamePassword() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "samePassword"; // security-sensitive test data

        // WHEN
        String encodedPassword1 = encoder.encode(rawPassword);
        String encodedPassword2 = encoder.encode(rawPassword);

        // THEN
        Assertions.assertNotNull(encodedPassword1, "First encoded password should not be null");
        Assertions.assertNotNull(encodedPassword2, "Second encoded password should not be null");
        Assertions.assertNotEquals(encodedPassword1, encodedPassword2, "BCrypt should produce different hashes for the same password due to salting");
        Assertions.assertTrue(encoder.matches(rawPassword, encodedPassword1), "First encoded password should match raw password");
        Assertions.assertTrue(encoder.matches(rawPassword, encodedPassword2), "Second encoded password should match raw password");
    }
}
