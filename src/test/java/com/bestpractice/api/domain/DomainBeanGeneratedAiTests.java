package com.bestpractice.api.domain;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
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
    void givenDomainBean_whenPasswordEncoderCalled_thenReturnBCryptPasswordEncoderInstance() {
        // GIVEN: a DomainBean instance
        // WHEN: calling passwordEncoder method
        PasswordEncoder encoder = domainBean.passwordEncoder();
        // THEN: verify the returned encoder is not null and is of type BCryptPasswordEncoder
        assertNotNull(encoder);
        assertEquals("org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder", encoder.getClass().getName());
    }

    @Test
    void givenPasswordEncoder_whenEncodingPassword_thenEncodedPasswordMatches() {
        // GIVEN: a PasswordEncoder from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "testPassword";
        // WHEN: encoding the password
        String encodedPassword = encoder.encode(rawPassword);
        // THEN: verify the encoded password matches the raw password using matches method
        assertTrue(encoder.matches(rawPassword, encodedPassword));
    }
}
