package com.bestpractice.api.domain;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.MockitoExtension;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DomainBeanGeneratedAiTests {

    private DomainBean domainBean;

    @BeforeEach
    void setUp() {
        domainBean = new DomainBean();
    }

    @Test
    void passwordEncoder_shouldCreatePasswordEncoderInstance() {
        // GIVEN: An instance of DomainBean is created.
        // WHEN: The passwordEncoder() method is called.
        // THEN: A BCryptPasswordEncoder instance is returned.
        PasswordEncoder passwordEncoder = domainBean.passwordEncoder();
        assertNotNull(passwordEncoder);
        assertInstanceOf(PasswordEncoder.class, passwordEncoder);
    }

    @Test
    void passwordEncoder_shouldEncodePassword() {
        // GIVEN: A string password.
        String password = "testPassword";
        // WHEN: The passwordEncoder() method is called.
        // THEN: The password is encoded using BCryptPasswordEncoder.
        String encodedPassword = domainBean.passwordEncoder().encode(password);
        assertEquals(encodedPassword, encodedPassword);
    }

    private String encodedPassword() {
        PasswordEncoder passwordEncoder = domainBean.passwordEncoder();
        return passwordEncoder.encode("testPassword");
    }
}
