package com.bestpractice.api.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DomainBeanGeneratedAiTests {

    private DomainBean domainBean;

    @BeforeEach
    void setUp() {
        // Reset the state before each test
        domainBean = new DomainBean();
    }

    @Test
    void givenDomainBean_whenPasswordEncoderCalled_thenReturnBCryptPasswordEncoder() {
        // GIVEN: A DomainBean instance

        // WHEN: The passwordEncoder method is called
        PasswordEncoder passwordEncoder = domainBean.passwordEncoder();

        // THEN: The returned PasswordEncoder should be an instance of BCryptPasswordEncoder
        assertNotNull(passwordEncoder);
        assertEquals(BCryptPasswordEncoder.class, passwordEncoder.getClass());
    }

    @Test
    void givenDomainBean_whenPasswordEncoderCalled_thenNoExceptionThrown() {
        // GIVEN: A DomainBean instance

        // WHEN: The passwordEncoder method is called
        PasswordEncoder passwordEncoder = domainBean.passwordEncoder();

        // THEN: No exception should be thrown
        assertNotNull(passwordEncoder);
    }

    @Test
    void givenDomainBean_whenPasswordEncoderCalled_thenHandleEdgeCase() {
        // GIVEN: A DomainBean instance

        // WHEN: The passwordEncoder method is called
        PasswordEncoder passwordEncoder = domainBean.passwordEncoder();

        // THEN: Ensure the returned object is not null and is of the correct type
        assertNotNull(passwordEncoder);
        assertEquals(BCryptPasswordEncoder.class, passwordEncoder.getClass());
    }
}
