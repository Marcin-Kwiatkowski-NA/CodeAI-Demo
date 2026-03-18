package com.bestpractice.api.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

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
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        DomainBean bean = domainBean;

        // WHEN
        PasswordEncoder encoder = bean.passwordEncoder();

        // THEN
        assertThat(encoder).isInstanceOf(BCryptPasswordEncoder.class);
    }

    @Test
    void shouldEncodeAndMatchPasswordCorrectly_securitySensitive() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "testPassword";

        // WHEN
        String encodedPassword = encoder.encode(rawPassword);

        // THEN
        assertThat(encodedPassword).isNotBlank();
        assertEquals(true, encoder.matches(rawPassword, encodedPassword));
    }

    @Test
    void shouldNotMatchDifferentPasswords_securitySensitive() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "passwordOne";
        String differentPassword = "passwordTwo";

        // WHEN
        String encodedPassword = encoder.encode(rawPassword);

        // THEN
        assertEquals(false, encoder.matches(differentPassword, encodedPassword));
    }

    @Test
    void shouldThrowExceptionWhenEncodingNullPassword_securitySensitive() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> encoder.encode(null));
    }

    @Test
    void shouldHandleNullValuesGracefullyWhenMatching_securitySensitive() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "password";

        // WHEN
        boolean result1 = encoder.matches(rawPassword, null);
        boolean result2 = encoder.matches(null, encoder.encode(rawPassword));

        // THEN
        assertEquals(false, result1);
        assertEquals(false, result2);
    }
}
