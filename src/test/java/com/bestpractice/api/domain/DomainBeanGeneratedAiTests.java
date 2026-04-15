package com.bestpractice.api.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
import org.junit.jupiter.api.Assertions;
import org.mockito.junit.jupiter.MockitoExtension;

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

        // THEN: verify that the returned encoder is an instance of BCryptPasswordEncoder
        Assertions.assertTrue(encoder instanceof BCryptPasswordEncoder);
    }

    @Test
    void passwordEncoder_ShouldEncodePasswordCorrectly() {
        // GIVEN: a PasswordEncoder from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "securePassword";

        // WHEN: encoding the password
        String encodedPassword = encoder.encode(rawPassword);

        // THEN: verify that the encoded password is not null and matches the raw password
        Assertions.assertNotNull(encodedPassword);
        Assertions.assertTrue(encoder.matches(rawPassword, encodedPassword));
    }

    @Test
    void passwordEncoder_ShouldNotMatchDifferentPasswords() {
        // GIVEN: a PasswordEncoder from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "passwordOne";
        String differentPassword = "passwordTwo";

        // WHEN: encoding the first password
        String encodedPassword = encoder.encode(rawPassword);

        // THEN: verify that the different password does not match the encoded one
        Assertions.assertFalse(encoder.matches(differentPassword, encodedPassword));
    }

    @Test
    void passwordEncoder_ShouldThrowException_WhenNullPasswordProvided() {
        // GIVEN: a PasswordEncoder from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();

        // WHEN & THEN: encoding a null password should throw an exception
        Assertions.assertThrows(IllegalArgumentException.class, () -> encoder.encode(null));
    }

    @Test
    void passwordEncoder_ShouldProduceDifferentHashesForSamePassword() {
        // GIVEN: a PasswordEncoder from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "samePassword";

        // WHEN: encoding the same password twice
        String encodedPassword1 = encoder.encode(rawPassword);
        String encodedPassword2 = encoder.encode(rawPassword);

        // THEN: verify that the two encoded passwords are different (due to random salt)
        Assertions.assertNotEquals(encodedPassword1, encodedPassword2);
        Assertions.assertTrue(encoder.matches(rawPassword, encodedPassword1));
        Assertions.assertTrue(encoder.matches(rawPassword, encodedPassword2));
    }
}
