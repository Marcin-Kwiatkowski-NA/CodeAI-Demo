package com.bestpractice.api.domain;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@org.junit.jupiter.api.TestContainer
public class DomainBeanGeneratedAiTests {

    @Autowired
    private DomainBean domainBean;

    @BeforeEach
    void setUp() {
        domainBean = new DomainBean();
    }

    @Test
    void passwordEncoder_shouldCreateNewInstance() {
        PasswordEncoder passwordEncoder = domainBean.passwordEncoder();
        assertNotNull(passwordEncoder);
    }
}
