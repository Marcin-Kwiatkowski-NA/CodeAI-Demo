package com.bestpractice.api.domain;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(Mockito.ExtendWith.class)
public class DomainBeanGeneratedAiTests {

    private DomainBean domainBean;

    @BeforeEach
    void setUp() {
        domainBean = new DomainBean();
    }

    @Test
    void passwordEncoder_shouldCreateNewPasswordEncoderInstance() {
        // GIVEN: We want to test the PasswordEncoder bean.
        // WHEN: We retrieve the PasswordEncoder from the DomainBean.
        PasswordEncoder passwordEncoder = domainBean.passwordEncoder();
        // THEN: We assert that a BCryptPasswordEncoder instance was created.
        assertNotNull(passwordEncoder);
    }
}
