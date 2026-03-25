package com.bestpractice.api.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
        // GIVEN: a DomainBean instance
        DomainBean bean = domainBean;

        // WHEN: calling passwordEncoder()
        PasswordEncoder encoder = bean.passwordEncoder();

        // THEN: verify that the returned encoder is a BCryptPasswordEncoder instance
        assertNotNull(encoder);
        assertEquals(BCryptPasswordEncoder.class, encoder.getClass());
    }

    @Test
    void passwordEncoder_ShouldEncodePasswordCorrectly() {
        // GIVEN: a PasswordEncoder from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "securePassword123"; // security-sensitive operation

        // WHEN: encoding the password
        String encodedPassword = encoder.encode(rawPassword);

        // THEN: verify that the encoded password is not null and matches the raw password when checked
        assertNotNull(encodedPassword);
        assertTrue(encoder.matches(rawPassword, encodedPassword));
    }

    @Test
    void passwordEncoder_ShouldNotMatchDifferentPasswords() {
        // GIVEN: a PasswordEncoder from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "securePassword123"; // security-sensitive operation
        String differentPassword = "wrongPassword456"; // security-sensitive operation

        // WHEN: encoding the raw password
        String encodedPassword = encoder.encode(rawPassword);

        // THEN: verify that a different password does not match the encoded one
        assertFalse(encoder.matches(differentPassword, encodedPassword));
    }

    @Test
    void passwordEncoder_ShouldThrowException_WhenInputIsNull() {
        // GIVEN: a PasswordEncoder from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();

        // WHEN & THEN: encoding a null password should throw an exception
        assertThrows(IllegalArgumentException.class, () -> encoder.encode(null));
    }

    @Test
    void passwordEncoder_ShouldProduceDifferentHashesForSamePassword() {
        // GIVEN: a PasswordEncoder from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "securePassword123"; // security-sensitive operation

        // WHEN: encoding the same password twice
        String encodedPassword1 = encoder.encode(rawPassword);
        String encodedPassword2 = encoder.encode(rawPassword);

        // THEN: verify that the two encoded passwords are different due to salting
        assertNotNull(encodedPassword1);
        assertNotNull(encodedPassword2);
        assertFalse(encodedPassword1.equals(encodedPassword2));
    }
}
