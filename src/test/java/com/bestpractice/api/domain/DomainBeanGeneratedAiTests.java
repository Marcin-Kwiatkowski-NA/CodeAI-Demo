package com.bestpractice.api.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

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
        // GIVEN: A DomainBean instance

        // WHEN: passwordEncoder method is called
        PasswordEncoder passwordEncoder = domainBean.passwordEncoder();

        // THEN: Verify the returned instance is of type BCryptPasswordEncoder
        assertEquals(BCryptPasswordEncoder.class, passwordEncoder.getClass());
    }

    @Test
    void givenDomainBean_whenPasswordEncoderInvoked_thenNoExceptionThrown() {
        // GIVEN: A DomainBean instance

        // WHEN: passwordEncoder method is called
        // THEN: Verify no exception is thrown
        PasswordEncoder passwordEncoder = domainBean.passwordEncoder();
        assertEquals(BCryptPasswordEncoder.class, passwordEncoder.getClass());
    }

    @Test
    void givenNullDomainBean_whenPasswordEncoderInvoked_thenHandleNullPointerException() {
        // GIVEN: A null DomainBean instance
        domainBean = null;

        // WHEN: passwordEncoder method is called
        // THEN: Verify NullPointerException is thrown
        assertThrows(NullPointerException.class, () -> {
            domainBean.passwordEncoder();
        });
    }

    @Test
    void givenDomainBean_whenPasswordEncoderInvoked_thenVerifyPasswordEncoderBehavior() {
        // GIVEN: A DomainBean instance and a PasswordEncoder
        PasswordEncoder passwordEncoder = domainBean.passwordEncoder();
        String rawPassword = "password123";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // WHEN: The password is encoded
        boolean matches = passwordEncoder.matches(rawPassword, encodedPassword);

        // THEN: Verify the encoded password matches the raw password
        assertEquals(true, matches);
    }

    @Test
    void givenDomainBean_whenPasswordEncoderInvoked_thenVerifyEncodingIsConsistent() {
        // GIVEN: A DomainBean instance and a PasswordEncoder
        PasswordEncoder passwordEncoder = domainBean.passwordEncoder();
        String rawPassword = "password123";

        // WHEN: The password is encoded multiple times
        String encodedPassword1 = passwordEncoder.encode(rawPassword);
        String encodedPassword2 = passwordEncoder.encode(rawPassword);

        // THEN: Verify the encoded passwords are not identical (BCrypt generates unique hashes)
        assertEquals(false, encodedPassword1.equals(encodedPassword2));
    }
}
