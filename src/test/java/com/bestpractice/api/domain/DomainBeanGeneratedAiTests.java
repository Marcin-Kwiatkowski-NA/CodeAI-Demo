package com.bestpractice.api.domain;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DomainBeanGeneratedAiTests {

    private DomainBean domainBean;

    @BeforeEach
    void setUp() {
        domainBean = new DomainBean();
    }

    @Test
    void passwordEncoder_shouldCreateNewInstance() {
        // GIVEN: A new instance of DomainBean is created.
        // WHEN: The passwordEncoder() method is called.
        // THEN: A new BCryptPasswordEncoder instance is returned.
        PasswordEncoder passwordEncoder = domainBean.passwordEncoder();
        assertNotNull(passwordEncoder);
    }
}
