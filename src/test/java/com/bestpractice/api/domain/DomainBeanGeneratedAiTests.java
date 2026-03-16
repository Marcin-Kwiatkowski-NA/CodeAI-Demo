package com.bestpractice.api.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * SECURITY-SENSITIVE: This test verifies password encoding and matching logic using BCryptPasswordEncoder.
 */
@ExtendWith(MockitoExtension.class)
public class DomainBeanGeneratedAiTests {

    private DomainBean domainBean;

    @BeforeEach
    void setUp() {
        domainBean = new DomainBean();
    }

    @Test
    void shouldReturnBCryptPasswordEncoderInstance() {
        // GIVEN - a DomainBean instance
        DomainBean bean = domainBean;

        // WHEN - calling passwordEncoder()
        PasswordEncoder encoder = bean.passwordEncoder();

        // THEN - verify that the returned encoder is a BCryptPasswordEncoder
        assertNotNull(encoder);
        assertEquals(BCryptPasswordEncoder.class, encoder.getClass());
    }

    @Test
    void shouldEncodePasswordCorrectly() {
        // GIVEN - a PasswordEncoder from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "testPassword";

        // WHEN - encoding the password
        String encodedPassword = encoder.encode(rawPassword);

        // THEN - verify that the encoded password is not null and matches the raw password
        assertNotNull(encodedPassword);
        assertTrue(encoder.matches(rawPassword, encodedPassword));
    }

    @Test
    void shouldNotMatchDifferentPasswords() {
        // GIVEN - a PasswordEncoder from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "passwordOne";
        String differentPassword = "passwordTwo";

        // WHEN - encoding the first password
        String encodedPassword = encoder.encode(rawPassword);

        // THEN - verify that the second password does not match the encoded one
        assertFalse(encoder.matches(differentPassword, encodedPassword));
    }

    @Test
    void shouldThrowExceptionWhenEncodingNullPassword() {
        // GIVEN - a PasswordEncoder from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();

        // WHEN & THEN - encoding a null password should throw an exception
        assertThrows(IllegalArgumentException.class, () -> encoder.encode(null));
    }

    @Test
    void shouldThrowExceptionWhenMatchingNullValues() {
        // GIVEN - a PasswordEncoder from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String encodedPassword = encoder.encode("validPassword");

        // WHEN & THEN - matching null raw password should throw an exception
        assertThrows(IllegalArgumentException.class, () -> encoder.matches(null, encodedPassword));
    }

    @Test
    void shouldThrowExceptionWhenMatchingWithNullEncodedPassword() {
        // GIVEN - a PasswordEncoder from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "validPassword";

        // WHEN & THEN - matching with null encoded password should throw an exception
        assertThrows(IllegalArgumentException.class, () -> encoder.matches(rawPassword, null));
    }
}
