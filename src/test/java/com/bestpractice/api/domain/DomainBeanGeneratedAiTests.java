package com.bestpractice.api.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class DomainBeanGeneratedAiTests {

    private DomainBean domainBean;

    @BeforeEach
    public void setUp() {
        domainBean = new DomainBean();
    }

    @Test
    public void testPasswordEncoderBeanCreation() {
        // GIVEN
        PasswordEncoder passwordEncoder = domainBean.passwordEncoder();

        // WHEN
        String encodedPassword = passwordEncoder.encode("testPassword");

        // THEN
        assertNotNull(encodedPassword);
        assertTrue(passwordEncoder.matches("testPassword", encodedPassword));
    }

    @Test
    public void testPasswordEncodingConsistency() {
        // GIVEN
        PasswordEncoder passwordEncoder = domainBean.passwordEncoder();
        String rawPassword = "consistentPassword";

        // WHEN
        String firstEncodedPassword = passwordEncoder.encode(rawPassword);
        String secondEncodedPassword = passwordEncoder.encode(rawPassword);

        // THEN
        assertNotEquals(firstEncodedPassword, secondEncodedPassword);  // BCrypt should generate different hashes for the same input
    }

    @Test
    public void testNullPasswordEncoding() {
        // GIVEN
        PasswordEncoder passwordEncoder = domainBean.passwordEncoder();

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> passwordEncoder.encode(null));
    }
}
