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
        // GIVEN - a DomainBean instance is initialized

        // WHEN - calling passwordEncoder() method
        PasswordEncoder encoder = domainBean.passwordEncoder();

        // THEN - verify that the returned encoder is a BCryptPasswordEncoder
        assertNotNull(encoder);
        assertEquals(BCryptPasswordEncoder.class, encoder.getClass());
    }

    @Test
    void passwordEncoder_ShouldEncodeAndMatchPasswordCorrectly() {
        // GIVEN - a PasswordEncoder bean from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "securePassword123"; // security-sensitive test data

        // WHEN - encoding the password
        String encodedPassword = encoder.encode(rawPassword);

        // THEN - verify that the encoded password matches the raw password
        assertNotNull(encodedPassword);
        assertTrue(encoder.matches(rawPassword, encodedPassword));
    }

    @Test
    void passwordEncoder_ShouldThrowException_WhenNullPasswordProvided() {
        // GIVEN - a PasswordEncoder bean from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();

        // WHEN & THEN - verify that encoding a null password throws an exception
        assertThrows(IllegalArgumentException.class, () -> encoder.encode(null));
    }

    @Test
    void passwordEncoder_ShouldProduceDifferentHashesForSamePassword() {
        // GIVEN - a PasswordEncoder bean from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "securePassword123"; // security-sensitive test data

        // WHEN - encoding the same password twice
        String encodedPassword1 = encoder.encode(rawPassword);
        String encodedPassword2 = encoder.encode(rawPassword);

        // THEN - verify that the two encoded passwords are different due to salting
        assertNotNull(encodedPassword1);
        assertNotNull(encodedPassword2);
        assertTrue(!encodedPassword1.equals(encodedPassword2));
    }
}
