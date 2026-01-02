package com.bestpractice.api.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DomainBeanGeneratedAiTests {

    private DomainBean domainBean;

    @BeforeEach
    void setUp() {
        domainBean = new DomainBean();
    }

    @Test
    void testPasswordEncoderBeanCreation() {
        // GIVEN a DomainBean instance
        // WHEN the passwordEncoder bean is requested
        PasswordEncoder encoder = domainBean.passwordEncoder();
        // THEN the returned instance should be a BCryptPasswordEncoder and not null
        assertThat(encoder).isNotNull();
        assertThat(encoder).isInstanceOf(BCryptPasswordEncoder.class);
    }

    @Test
    void testPasswordEncoderEncodesPassword() {
        // GIVEN a PasswordEncoder bean from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        // WHEN a plain text password is encoded
        String rawPassword = "MySecretPassword123";
        String encodedPassword = encoder.encode(rawPassword);
        // THEN the encoded password should not be equal to the raw password
        assertThat(encodedPassword).isNotEqualTo(rawPassword);
        // AND the encoded password should match when verified
        assertThat(encoder.matches(rawPassword, encodedPassword)).isTrue();
    }

    @Test
    void testPasswordEncoderEncodeNullThrowsException() {
        // GIVEN a PasswordEncoder bean from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        // WHEN attempting to encode a null password
        // THEN an IllegalArgumentException should be thrown
        assertThrows(IllegalArgumentException.class, () -> encoder.encode(null));
    }

    @Test
    void testPasswordEncoderMatchesNullThrowsException() {
        // GIVEN a PasswordEncoder bean from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        // WHEN attempting to match a null raw password
        // THEN an IllegalArgumentException should be thrown
        assertThrows(IllegalArgumentException.class, () -> encoder.matches(null, "anyEncodedPassword"));
    }

    @Test
    void testPasswordEncoderProducesDifferentHashesForSamePassword() {
        // GIVEN a PasswordEncoder bean from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        // WHEN the same raw password is encoded twice
        String rawPassword = "SamePassword";
        String firstHash = encoder.encode(rawPassword);
        String secondHash = encoder.encode(rawPassword);
        // THEN the two hashes should be different due to random salt
        assertThat(firstHash).isNotEqualTo(secondHash);
        // AND both hashes should match the raw password
        assertThat(encoder.matches(rawPassword, firstHash)).isTrue();
        assertThat(encoder.matches(rawPassword, secondHash)).isTrue();
    }

    @Test
    void testPasswordEncoderMatchesReturnsFalseForWrongPassword() {
        // GIVEN a PasswordEncoder bean from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        // WHEN a password is encoded
        String rawPassword = "CorrectPassword";
        String encodedPassword = encoder.encode(rawPassword);
        // AND a wrong password is checked against the encoded value
        boolean matches = encoder.matches("WrongPassword", encodedPassword);
        // THEN the result should be false
        assertThat(matches).isFalse();
    }
}
