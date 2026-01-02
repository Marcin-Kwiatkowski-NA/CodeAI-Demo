package com.bestpractice.api.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DomainBeanGeneratedAiTests {

    private DomainBean domainBean;

    @BeforeEach
    void setUp() {
        domainBean = new DomainBean();
    }

    @Test
    void passwordEncoderBeanIsInstanceOfBCryptPasswordEncoder() {
        // GIVEN a DomainBean instance
        // WHEN calling passwordEncoder()
        PasswordEncoder encoder = domainBean.passwordEncoder();
        // THEN it should be an instance of BCryptPasswordEncoder
        assertThat(encoder).isInstanceOf(BCryptPasswordEncoder.class);
    }

    @Test
    void passwordEncoderEncodesAndMatchesPassword() {
        // GIVEN a PasswordEncoder bean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "SecurePass123!";
        // WHEN encoding the raw password
        String encodedPassword = encoder.encode(rawPassword);
        // THEN the encoded password should not be equal to raw password
        assertThat(encodedPassword).isNotEqualTo(rawPassword);
        // AND the encoder should validate the raw password against the encoded one
        assertThat(encoder.matches(rawPassword, encodedPassword)).isTrue();
    }

    @Test
    void passwordEncoderEncodeNullThrowsIllegalArgumentException() {
        // GIVEN a PasswordEncoder bean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        // WHEN attempting to encode a null password
        // THEN an IllegalArgumentException should be thrown
        assertThrows(IllegalArgumentException.class, () -> encoder.encode(null));
    }

    @Test
    void passwordEncoderMatchesNullRawPasswordThrowsIllegalArgumentException() {
        // GIVEN a PasswordEncoder bean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String encodedPassword = encoder.encode("somePassword");
        // WHEN attempting to match a null raw password
        // THEN an IllegalArgumentException should be thrown
        assertThrows(IllegalArgumentException.class, () -> encoder.matches(null, encodedPassword));
    }

    @Test
    void passwordEncoderMatchesWithWrongPasswordReturnsFalse() {
        // GIVEN a PasswordEncoder bean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "CorrectPassword";
        String wrongPassword = "WrongPassword";
        // WHEN encoding the correct password
        String encodedPassword = encoder.encode(rawPassword);
        // THEN matching with a wrong password should return false
        assertThat(encoder.matches(wrongPassword, encodedPassword)).isFalse();
    }
}
