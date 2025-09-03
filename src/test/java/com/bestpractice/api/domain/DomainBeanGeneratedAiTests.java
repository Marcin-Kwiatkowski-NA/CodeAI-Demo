package com.bestpractice.api.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyAnnotations.class)
class DomainBeanGeneratedAiTests {

    private DomainBean domainBean;

    @BeforeEach
    void setUp() {
        domainBean = new DomainBean();
    }

    @Test
    void passwordEncoder_shouldCreateNewBCryptPasswordEncoderInstance() {
        // GIVEN: We have an instance of DomainBean
        // WHEN: We call the passwordEncoder() method
        // THEN: A new BCryptPasswordEncoder instance should be returned
        PasswordEncoder passwordEncoder = domainBean.passwordEncoder();
        assertEquals(passwordEncoder instanceof BCryptPasswordEncoder, "Password encoder should be BCryptPasswordEncoder");
    }
}

class MyAnnotations {
}
