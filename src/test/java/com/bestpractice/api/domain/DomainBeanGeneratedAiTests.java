package com.bestpractice.api.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class DomainBeanGeneratedAiTests {

    private DomainBean domainBean;

    @BeforeEach
    void setUp() {
        domainBean = new DomainBean();
    }

    @Test
    void shouldReturnBCryptPasswordEncoderInstance() {
        // GIVEN
        // DomainBean instance is initialized in setUp()

        // WHEN
        PasswordEncoder encoder = domainBean.passwordEncoder();

        // THEN
        assertNotNull(encoder);
        assertTrue(encoder instanceof BCryptPasswordEncoder);
    }

    @Test
    void shouldEncodeAndMatchPasswordCorrectly_securitySensitive() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "testPassword";

        // WHEN
        String encodedPassword = encoder.encode(rawPassword);
        boolean matches = encoder.matches(rawPassword, encodedPassword);

        // THEN
        assertNotNull(encodedPassword);
        assertTrue(matches);
    }

    @Test
    void shouldHandleNullPasswordGracefullyWhenMatching_securitySensitive() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "testPassword";
        String encodedPassword = encoder.encode(rawPassword);

        // WHEN
        boolean result1 = encoder.matches(null, encodedPassword);
        boolean result2 = encoder.matches(rawPassword, null);

        // THEN
        assertEquals(false, result1);
        assertEquals(false, result2);
    }

    @Test
    void shouldGenerateDifferentHashesForSamePassword_securitySensitive() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "testPassword";

        // WHEN
        String encoded1 = encoder.encode(rawPassword);
        String encoded2 = encoder.encode(rawPassword);

        // THEN
        assertNotNull(encoded1);
        assertNotNull(encoded2);
        assertThat(encoded1).isNotEqualTo(encoded2);
    }
}
