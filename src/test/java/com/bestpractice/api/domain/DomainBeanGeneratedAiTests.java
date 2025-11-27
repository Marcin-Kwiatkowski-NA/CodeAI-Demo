package com.bestpractice.api.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
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
    void givenDomainBean_whenPasswordEncoderInvoked_thenReturnsBCryptPasswordEncoderInstance() {
        // GIVEN: A DomainBean instance is initialized

        // WHEN: The passwordEncoder method is called
        PasswordEncoder passwordEncoder = domainBean.passwordEncoder();

        // THEN: The returned PasswordEncoder instance should be of type BCryptPasswordEncoder
        assertEquals(BCryptPasswordEncoder.class, passwordEncoder.getClass());
    }

    @Test
    void givenDomainBean_whenPasswordEncoderInvoked_thenDoesNotThrowException() {
        // GIVEN: A DomainBean instance is initialized

        // WHEN: The passwordEncoder method is called
        // THEN: Ensure no exception is thrown
        PasswordEncoder passwordEncoder = domainBean.passwordEncoder();
        assertEquals(BCryptPasswordEncoder.class, passwordEncoder.getClass());
    }

    @Test
    void givenNullDomainBean_whenPasswordEncoderInvoked_thenThrowsNullPointerException() {
        // GIVEN: A null DomainBean instance
        DomainBean nullDomainBean = null;

        // WHEN: The passwordEncoder method is called on a null instance
        // THEN: Ensure NullPointerException is thrown
        assertThrows(NullPointerException.class, () -> {
            PasswordEncoder passwordEncoder = nullDomainBean.passwordEncoder();
        });
    }

    @Test
    void givenDomainBean_whenPasswordEncoderInvoked_thenHandlesMisconfigurationGracefully() {
        // GIVEN: A DomainBean instance with potential misconfiguration
        DomainBean misconfiguredDomainBean = new DomainBean() {
            @Override
            public PasswordEncoder passwordEncoder() {
                throw new IllegalStateException("Misconfiguration detected");
            }
        };

        // WHEN: The passwordEncoder method is called
        // THEN: Ensure IllegalStateException is thrown
        assertThrows(IllegalStateException.class, misconfiguredDomainBean::passwordEncoder);
    }
}
