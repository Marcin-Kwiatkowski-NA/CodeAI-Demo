package com.bestpractice.api.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DomainBeanGeneratedAiTests {

    private AnnotationConfigApplicationContext context;

    @BeforeEach
    public void setUp() {
        context = new AnnotationConfigApplicationContext(DomainBean.class);
    }

    @Test
    public void shouldReturnBCryptPasswordEncoderInstance() {
        // GIVEN
        PasswordEncoder passwordEncoder = context.getBean(PasswordEncoder.class);

        // WHEN
        PasswordEncoder result = passwordEncoder;

        // THEN
        assertNotNull(result);
        assertTrue(result instanceof BCryptPasswordEncoder);
    }

    @Test
    public void shouldEncodePasswordWithBCrypt() {
        // GIVEN
        PasswordEncoder passwordEncoder = context.getBean(PasswordEncoder.class);
        String plainPassword = "test123";

        // WHEN
        String encodedPassword = passwordEncoder.encode(plainPassword);

        // THEN
        assertNotNull(encodedPassword);
        assertNotEquals(plainPassword, encodedPassword);
    }

    @Test
    public void shouldVerifyPasswordCorrectly() {
        // GIVEN
        PasswordEncoder passwordEncoder = context.getBean(PasswordEncoder.class);
        String plainPassword = "test123";
        String encodedPassword = passwordEncoder.encode(plainPassword);

        // WHEN
        boolean isValid = passwordEncoder.matches(plainPassword, encodedPassword);

        // THEN
        assertTrue(isValid);
    }

    @Test
    public void shouldThrowExceptionWhenEncodingNullPassword() {
        // GIVEN
        PasswordEncoder passwordEncoder = context.getBean(PasswordEncoder.class);

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            passwordEncoder.encode(null);
        }, "Encoding null password should throw IllegalArgumentException");
    }

    @Test
    public void shouldThrowExceptionWhenVerifyingNullPassword() {
        // GIVEN
        PasswordEncoder passwordEncoder = context.getBean(PasswordEncoder.class);

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            passwordEncoder.matches(null, "any");
        }, "Verifying with null password should throw IllegalArgumentException");
    }

    @Test
    public void shouldThrowExceptionWhenVerifyingNullEncodedPassword() {
        // GIVEN
        PasswordEncoder passwordEncoder = context.getBean(PasswordEncoder.class);

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            passwordEncoder.matches("any", null);
        }, "Verifying with null encoded password should throw IllegalArgumentException");
    }
}
