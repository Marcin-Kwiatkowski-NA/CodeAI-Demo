package com.bestpractice.api.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
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
    void passwordEncoder_ShouldReturnBCryptPasswordEncoderInstance() {
        // GIVEN - DomainBean instance is initialized in setUp()

        // WHEN - invoke passwordEncoder method
        PasswordEncoder encoder = domainBean.passwordEncoder();

        // THEN - verify returned instance type and behavior
        assertThat(encoder).isInstanceOf(BCryptPasswordEncoder.class);
        String encoded = encoder.encode("test");
        assertThat(encoded).isNotBlank();
    }

    @Test
    void passwordEncoder_ShouldEncodeAndMatchPasswordCorrectly() {
        // GIVEN - create encoder from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "securePassword";

        // WHEN - encode password and verify match
        String encodedPassword = encoder.encode(rawPassword);

        // THEN - assert encoding and matching
        assertThat(encodedPassword).isNotEqualTo(rawPassword);
        assertThat(encoder.matches(rawPassword, encodedPassword)).isTrue();
    }

    @Test
    void passwordEncoder_ShouldThrowException_WhenNullInputProvided() {
        // GIVEN - create encoder from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();

        // WHEN & THEN - verify that encoding null throws an exception
        assertThrows(IllegalArgumentException.class, () -> encoder.encode(null));
    }

    @Test
    void passwordEncoder_ShouldProduceDifferentHashesForSameInput() {
        // GIVEN - create encoder from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "samePassword";

        // WHEN - encode the same password twice
        String encoded1 = encoder.encode(rawPassword);
        String encoded2 = encoder.encode(rawPassword);

        // THEN - assert that hashes are different due to salting
        assertThat(encoded1).isNotEqualTo(encoded2);
        assertThat(encoder.matches(rawPassword, encoded1)).isTrue();
        assertThat(encoder.matches(rawPassword, encoded2)).isTrue();
    }

    @Test
    void passwordEncoder_ShouldHandleEmptyStringInputGracefully() {
        // GIVEN - create encoder from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String emptyPassword = "";

        // WHEN - encode empty string
        String encodedEmpty = encoder.encode(emptyPassword);

        // THEN - verify encoding result is not blank and matches correctly
        assertThat(encodedEmpty).isNotBlank();
        assertThat(encoder.matches(emptyPassword, encodedEmpty)).isTrue();
    }

    @Test
    void passwordEncoder_ShouldReturnConsistentBehaviorAcrossInstances() {
        // GIVEN - two separate DomainBean instances
        DomainBean anotherDomainBean = new DomainBean();

        // WHEN - obtain encoders from both instances
        PasswordEncoder encoder1 = domainBean.passwordEncoder();
        PasswordEncoder encoder2 = anotherDomainBean.passwordEncoder();

        // THEN - verify both are instances of BCryptPasswordEncoder
        assertThat(encoder1).isInstanceOf(BCryptPasswordEncoder.class);
        assertThat(encoder2).isInstanceOf(BCryptPasswordEncoder.class);
        assertEquals(encoder1.getClass(), encoder2.getClass());
    }
}
