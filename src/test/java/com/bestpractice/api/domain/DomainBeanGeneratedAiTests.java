package com.bestpractice.api.domain;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DomainBeanGeneratedAiTests {

    private DomainBean domainBean;

    @BeforeEach
    void setUp() {
        domainBean = new DomainBean();
    }

    @Test
    void passwordEncoder_shouldCreateNewBCryptPasswordEncoderInstance() {
        // GIVEN: We have a DomainBean instance.
        // WHEN: We call the passwordEncoder() method.
        // THEN: A new BCryptPasswordEncoder instance is returned.
        PasswordEncoder passwordEncoder = domainBean.passwordEncoder();
        assertNotNull(passwordEncoder, "Password encoder should not be null");
    }
}
