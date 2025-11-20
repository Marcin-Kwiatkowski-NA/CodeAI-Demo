package com.bestpractice.api.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class DomainBeanGeneratedAiTests {

    private DomainBean domainBean;

    @BeforeEach
    void setUp() {
        domainBean = new DomainBean();
    }

    @Test
    void givenDomainBean_whenPasswordEncoderInvoked_thenReturnsBCryptPasswordEncoderInstance() {
        // GIVEN
        // DomainBean is already initialized in the setup.

        // WHEN
        PasswordEncoder passwordEncoder = domainBean.passwordEncoder();

        // THEN
        assertNotNull(passwordEncoder, "PasswordEncoder should not be null");
        assertEquals(BCryptPasswordEncoder.class, passwordEncoder.getClass(), "PasswordEncoder should be an instance of BCryptPasswordEncoder");
    }

    @Test
    void givenDomainBean_whenPasswordEncoderInvoked_thenDoesNotThrowException() {
        // GIVEN
        // DomainBean is already initialized in the setup.

        // WHEN
        PasswordEncoder passwordEncoder = null;

        // THEN
        try {
            passwordEncoder = domainBean.passwordEncoder();
            assertNotNull(passwordEncoder, "PasswordEncoder should not be null");
        } catch (Exception e) {
            throw new AssertionError("PasswordEncoder invocation should not throw an exception", e);
        }
    }

    @Test
    void givenNullDomainBean_whenPasswordEncoderInvoked_thenThrowsNullPointerException() {
        // GIVEN
        DomainBean nullDomainBean = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            PasswordEncoder passwordEncoder = nullDomainBean.passwordEncoder();
        }, "Expected NullPointerException when DomainBean is null");
    }
}
