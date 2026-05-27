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
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * SECURITY-SENSITIVE: This test verifies password encoding behavior using BCryptPasswordEncoder.
 * Ensure no real passwords or sensitive data are used in tests.
 */
@ExtendWith(MockitoExtension.class)
public class DomainBeanGeneratedAiTests {

    private DomainBean domainBean;

    @BeforeEach
    void setUp() {
        domainBean = new DomainBean();
    }

    @Test
    void passwordEncoder_ShouldReturnBCryptPasswordEncoderInstance() {
        // GIVEN: a DomainBean instance
        DomainBean bean = domainBean;

        // WHEN: calling passwordEncoder()
        PasswordEncoder encoder = bean.passwordEncoder();

        // THEN: verify that the returned encoder is a BCryptPasswordEncoder
        assertEquals(BCryptPasswordEncoder.class, encoder.getClass());
    }

    @Test
    void passwordEncoder_ShouldEncodePasswordCorrectly() {
        // GIVEN: a PasswordEncoder from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "testPassword";

        // WHEN: encoding the password
        String encodedPassword = encoder.encode(rawPassword);

        // THEN: verify that the encoded password matches the raw password when checked
        assertTrue(encoder.matches(rawPassword, encodedPassword));
    }

    @Test
    void passwordEncoder_ShouldProduceDifferentHashesForSamePassword() {
        // GIVEN: a PasswordEncoder from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "samePassword";

        // WHEN: encoding the same password twice
        String encoded1 = encoder.encode(rawPassword);
        String encoded2 = encoder.encode(rawPassword);

        // THEN: verify that the two encoded values are different
        assertNotEquals(encoded1, encoded2);
    }

    @Test
    void passwordEncoder_ShouldThrowException_WhenPasswordIsNull() {
        // GIVEN: a PasswordEncoder from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();

        // WHEN & THEN: encoding a null password should throw an exception
        assertThrows(IllegalArgumentException.class, () -> encoder.encode(null));
    }

    @Test
    void passwordEncoder_ShouldThrowException_WhenPasswordIsEmpty() {
        // GIVEN: a PasswordEncoder from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String emptyPassword = "";

        // WHEN & THEN: encoding an empty password should throw an exception
        assertThrows(IllegalArgumentException.class, () -> encoder.encode(emptyPassword));
    }
}
