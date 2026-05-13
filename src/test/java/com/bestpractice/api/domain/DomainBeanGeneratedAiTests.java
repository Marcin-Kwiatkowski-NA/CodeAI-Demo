package com.bestpractice.api.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
        assertNotNull(encoder);
        assertEquals(BCryptPasswordEncoder.class, encoder.getClass());
    }

    @Test
    void passwordEncoder_ShouldEncodePasswordCorrectly() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "securePassword123"; // security-sensitive: password encoding test

        // WHEN
        String encodedPassword = encoder.encode(rawPassword);

        // THEN
        assertNotNull(encodedPassword);
        assertNotEquals(rawPassword, encodedPassword);
        assertTrue(encoder.matches(rawPassword, encodedPassword));
    }

    @Test
    void passwordEncoder_ShouldThrowException_WhenPasswordIsNull() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> encoder.encode(null));
    }

    @Test
    void passwordEncoder_ShouldNotMatchIncorrectPassword() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "securePassword123"; // security-sensitive: password encoding test
        String encodedPassword = encoder.encode(rawPassword);

        // WHEN
        boolean matches = encoder.matches("wrongPassword", encodedPassword);

        // THEN
        assertTrue(!matches);
    }
}
