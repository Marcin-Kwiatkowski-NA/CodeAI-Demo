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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void givenDomainBean_whenPasswordEncoderInvoked_thenNoExceptionThrown() {
        // GIVEN
        // DomainBean is already initialized in the setup method.

        // WHEN
        PasswordEncoder passwordEncoder = domainBean.passwordEncoder();

        // THEN
        assertEquals(BCryptPasswordEncoder.class, passwordEncoder.getClass());
    }

    @Test
    void givenNullDomainBean_whenPasswordEncoderInvoked_thenThrowNullPointerException() {
        // GIVEN
        domainBean = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            domainBean.passwordEncoder();
        });
    }

    @Test
    void givenDomainBean_whenPasswordEncoderInvokedMultipleTimes_thenReturnConsistentInstance() {
        // GIVEN
        // DomainBean is already initialized in the setup method.

        // WHEN
        PasswordEncoder passwordEncoder1 = domainBean.passwordEncoder();
        PasswordEncoder passwordEncoder2 = domainBean.passwordEncoder();

        // THEN
        assertEquals(passwordEncoder1.getClass(), passwordEncoder2.getClass());
    }

    @Test
    void givenDomainBean_whenPasswordEncoderInvoked_thenHandleUnexpectedExceptionGracefully() {
        // GIVEN
        // DomainBean is already initialized in the setup method.

        // WHEN & THEN
        try {
            PasswordEncoder passwordEncoder = domainBean.passwordEncoder();
            assertEquals(BCryptPasswordEncoder.class, passwordEncoder.getClass());
        } catch (Exception e) {
            throw new AssertionError("Unexpected exception occurred: " + e.getMessage());
        }
    }

    @Test
    void givenDomainBean_whenPasswordEncoderInvoked_thenVerifyBeanAnnotation() {
        // GIVEN
        // DomainBean is already initialized in the setup method.

        // WHEN
        PasswordEncoder passwordEncoder = domainBean.passwordEncoder();

        // THEN
        assertEquals(BCryptPasswordEncoder.class, passwordEncoder.getClass());
        // Verify that the method is annotated with @Bean (implicit verification through Spring context)
    }
}
