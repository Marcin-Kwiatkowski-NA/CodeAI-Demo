package com.bestpractice.api.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class DomainBeanGeneratedAiTests {

    private DomainBean domainBean;

    @BeforeEach
    void setUp() {
        // GIVEN
        domainBean = new DomainBean();
    }

    @Test
    void passwordEncoderShouldNotBeNull() {
        // GIVEN
        // (setUp already creates DomainBean instance)

        // WHEN
        PasswordEncoder encoder = domainBean.passwordEncoder();

        // THEN
        Assertions.assertThat(encoder).isNotNull();
    }

    @Test
    void passwordEncoderShouldBeInstanceOfBCryptPasswordEncoder() {
        // GIVEN
        // (setUp already creates DomainBean instance)

        // WHEN
        PasswordEncoder encoder = domainBean.passwordEncoder();

        // THEN
        Assertions.assertThat(encoder).isInstanceOf(BCryptPasswordEncoder.class);
    }

    @Test
    void passwordEncoderShouldEncodeAndMatchPassword() {
        // GIVEN
        String rawPassword = "mySecretPassword";
        PasswordEncoder encoder = domainBean.passwordEncoder();

        // WHEN
        String encodedPassword = encoder.encode(rawPassword);

        // THEN
        Assertions.assertThat(encodedPassword).isNotNull();
        Assertions.assertThat(encoder.matches(rawPassword, encodedPassword)).isTrue();
    }
}
