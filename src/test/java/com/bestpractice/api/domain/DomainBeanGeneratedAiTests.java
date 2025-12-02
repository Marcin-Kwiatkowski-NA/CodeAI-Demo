package com.bestpractice.api.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class DomainBeanGeneratedAiTests {

    private DomainBean domainBean;

    @BeforeEach
    void setUp() {
        domainBean = new DomainBean();
    }

    @Test
    void givenDomainBean_whenPasswordEncoderInvoked_thenReturnBCryptPasswordEncoderInstance() {
        // GIVEN
        // DomainBean is already initialized in the setup method.

        // WHEN
        PasswordEncoder passwordEncoder = domainBean.passwordEncoder();

        // THEN
        assertEquals(BCryptPasswordEncoder.class, passwordEncoder.getClass());
    }

    @Test
    void givenDomainBean_whenPasswordEncoderInvoked_thenEnsurePasswordEncoderIsNotNull() {
        // GIVEN
        // DomainBean is already initialized in the setup method.

        // WHEN
        PasswordEncoder passwordEncoder = domainBean.passwordEncoder();

        // THEN
        assertNotNull(passwordEncoder);
    }

    @Test
    void givenDomainBean_whenPasswordEncoderInvoked_thenNoExceptionThrown() {
        // GIVEN
        // DomainBean is already initialized in the setup method.

        // WHEN
        PasswordEncoder passwordEncoder = domainBean.passwordEncoder();

        // THEN
        assertEquals(BCryptPasswordEncoder.class, passwordEncoder.getClass());
    }
}
