package com.bestpractice.api.domain;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

public class DomainBeanGeneratedAiTests {

    private DomainBean domainBean;

    @BeforeEach
    void setUp() {
        domainBean = new DomainBean();
    }

    @Test
    void testPasswordEncoderBeanCreation() {
        // GIVEN: a DomainBean instance
        // WHEN: calling passwordEncoder method
        PasswordEncoder encoder = domainBean.passwordEncoder();
        // THEN: the returned encoder should not be null
        assertNotNull(encoder);
    }

    @Test
    void testPasswordEncoderFunctionality() {
        // GIVEN: a DomainBean instance and a raw password
        String rawPassword = "testPassword";
        PasswordEncoder encoder = domainBean.passwordEncoder();
        // WHEN: encoding the raw password
        String encodedPassword = encoder.encode(rawPassword);
        // THEN: the encoded password should match the raw password when verified
        assertTrue(encoder.matches(rawPassword, encodedPassword));
    }
}
