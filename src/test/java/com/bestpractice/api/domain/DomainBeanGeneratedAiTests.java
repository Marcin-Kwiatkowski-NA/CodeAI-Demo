package com.bestpractice.api.domain;

import static org.assertj.core.api.Assertions.assertThat;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DomainBeanGeneratedAiTests {

    private DomainBean domainBean;

    @BeforeEach
    void setUp() {
        // Reset state before each test
        domainBean = new DomainBean();
    }

    @Test
    void givenDomainBean_whenPasswordEncoderInvoked_thenReturnBCryptPasswordEncoderInstance() {
        // GIVEN: A DomainBean instance

        // WHEN: The passwordEncoder method is called
        PasswordEncoder passwordEncoder = domainBean.passwordEncoder();

        // THEN: Verify the returned instance is of type BCryptPasswordEncoder
        assertEquals(BCryptPasswordEncoder.class, passwordEncoder.getClass());
    }

    @Test
    void givenDomainBean_whenPasswordEncoderInvoked_thenNoExceptionThrown() {
        // GIVEN: A DomainBean instance

        // WHEN: The passwordEncoder method is called
        PasswordEncoder passwordEncoder = domainBean.passwordEncoder();

        // THEN: Verify no exception is thrown
        assertEquals(BCryptPasswordEncoder.class, passwordEncoder.getClass());
    }

    @Test
    void givenNullDomainBean_whenPasswordEncoderInvoked_thenHandleNullPointerException() {
        // GIVEN: A null DomainBean instance
        domainBean = null;

        // WHEN: The passwordEncoder method is called
        // THEN: Verify NullPointerException is thrown
        assertThrows(NullPointerException.class, () -> {
            domainBean.passwordEncoder();
        });
    }

    @Test
    void givenDomainBean_whenPasswordEncoderInvoked_thenVerifyPasswordEncoderBehavior() {
        // GIVEN: A DomainBean instance and a PasswordEncoder
        PasswordEncoder passwordEncoder = domainBean.passwordEncoder();
        String rawPassword = "testPassword";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // WHEN: The encode method is called
        boolean matches = passwordEncoder.matches(rawPassword, encodedPassword);

        // THEN: Verify the encoded password matches the raw password
        assertEquals(true, matches);
    }

    @Test
    void givenDomainBean_whenPasswordEncoderInvoked_thenVerifyEncodingIsConsistent() {
        // GIVEN: A DomainBean instance and a PasswordEncoder
        PasswordEncoder passwordEncoder = domainBean.passwordEncoder();
        String rawPassword = "consistentPassword";

        // WHEN: The encode method is called multiple times
        String encodedPassword1 = passwordEncoder.encode(rawPassword);
        String encodedPassword2 = passwordEncoder.encode(rawPassword);

        // THEN: Verify that encoded passwords are different due to BCrypt salting
        assertEquals(false, encodedPassword1.equals(encodedPassword2));
    }

    @Test
    void givenDomainBean_whenPasswordEncoderInvoked_thenVerifyNullRawPasswordThrowsException() {
        // GIVEN: A DomainBean instance and a PasswordEncoder
        PasswordEncoder passwordEncoder = domainBean.passwordEncoder();
        String rawPassword = null;

        // WHEN: The encode method is called with a null raw password
        // THEN: Verify IllegalArgumentException is thrown
        assertThrows(IllegalArgumentException.class, () -> {
            passwordEncoder.encode(rawPassword);
        });
    }
}
