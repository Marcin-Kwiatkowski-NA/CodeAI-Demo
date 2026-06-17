package com.bestpractice.api.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class DomainBeanGeneratedAiTests {

    private DomainBean domainBean;

    @BeforeEach
    void setUp() {
        domainBean = new DomainBean();
    }

    @Test
    void passwordEncoder_ShouldReturnNonNullInstance() {
        PasswordEncoder encoder = domainBean.passwordEncoder();
        assertThat(encoder).isNotNull();
    }

    @Test
    void passwordEncoder_ShouldEncodePasswordCorrectly() {
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "testPassword";
        String encodedPassword = encoder.encode(rawPassword);
        assertThat(encodedPassword).isNotBlank();
        assertThat(encoder.matches(rawPassword, encodedPassword)).isTrue();
    }

    @Test
    void passwordEncoder_ShouldNotMatchDifferentPasswords() {
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "password1";
        String differentPassword = "password2";
        String encodedPassword = encoder.encode(rawPassword);
        assertThat(encoder.matches(differentPassword, encodedPassword)).isFalse();
    }

    @Test
    void passwordEncoder_ShouldProduceDifferentHashesForSamePassword() {
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String password = "samePassword";
        String encoded1 = encoder.encode(password);
        String encoded2 = encoder.encode(password);
        assertThat(encoded1).isNotEqualTo(encoded2);
        assertThat(encoder.matches(password, encoded1)).isTrue();
        assertThat(encoder.matches(password, encoded2)).isTrue();
    }

    @Test
    void passwordEncoder_ShouldHandleEmptyStringPassword() {
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String emptyPassword = "";
        String encodedPassword = encoder.encode(emptyPassword);
        assertThat(encodedPassword).isNotBlank();
        assertThat(encoder.matches(emptyPassword, encodedPassword)).isTrue();
    }

    @Test
    void passwordEncoder_ShouldHandleWhitespaceOnlyPassword() {
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String whitespacePassword = "   ";
        String encodedPassword = encoder.encode(whitespacePassword);
        assertThat(encodedPassword).isNotBlank();
        assertThat(encoder.matches(whitespacePassword, encodedPassword)).isTrue();
    }

    @Test
    void passwordEncoder_ShouldHandleSingleCharacterPassword() {
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String singleCharPassword = "a";
        String encodedPassword = encoder.encode(singleCharPassword);
        assertThat(encodedPassword).isNotBlank();
        assertThat(encoder.matches(singleCharPassword, encodedPassword)).isTrue();
    }

    @Test
    void passwordEncoder_ShouldHandleVeryLongPassword() {
        PasswordEncoder encoder = domainBean.passwordEncoder();
        StringBuilder longPasswordBuilder = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            longPasswordBuilder.append("a");
        }
        String longPassword = longPasswordBuilder.toString();
        String encodedPassword = encoder.encode(longPassword);
        assertThat(encodedPassword).isNotBlank();
        assertThat(encoder.matches(longPassword, encodedPassword)).isTrue();
    }

    @Test
    void passwordEncoder_ShouldHandlePasswordWithSpecialCharacters() {
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String specialCharPassword = "!@#$%^&*()_+-=[]{}|;':,.<>?/`~";
        String encodedPassword = encoder.encode(specialCharPassword);
        assertThat(encodedPassword).isNotBlank();
        assertThat(encoder.matches(specialCharPassword, encodedPassword)).isTrue();
    }

    @Test
    void passwordEncoder_ShouldHandlePasswordWithUnicodeCharacters() {
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String unicodePassword = "pässwörd😊";
        String encodedPassword = encoder.encode(unicodePassword);
        assertThat(encodedPassword).isNotBlank();
        assertThat(encoder.matches(unicodePassword, encodedPassword)).isTrue();
    }

    @Test
    void passwordEncoder_ShouldHandlePasswordWithMixedCase() {
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String mixedCasePassword = "Password123";
        String encodedPassword = encoder.encode(mixedCasePassword);
        assertThat(encodedPassword).isNotBlank();
        assertThat(encoder.matches(mixedCasePassword, encodedPassword)).isTrue();
    }

    @Test
    void passwordEncoder_ShouldHandlePasswordWithLeadingAndTrailingSpaces() {
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String passwordWithSpaces = "  password  ";
        String encodedPassword = encoder.encode(passwordWithSpaces);
        assertThat(encodedPassword).isNotBlank();
        assertThat(encoder.matches(passwordWithSpaces, encodedPassword)).isTrue();
    }

    @Test
    void passwordEncoder_ShouldHandlePasswordWithNumericOnlyCharacters() {
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String numericPassword = "1234567890";
        String encodedPassword = encoder.encode(numericPassword);
        assertThat(encodedPassword).isNotBlank();
        assertThat(encoder.matches(numericPassword, encodedPassword)).isTrue();
    }

    @Test
    void passwordEncoder_ShouldHandlePasswordWithRepeatedCharacters() {
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String repeatedPassword = "aaaaaa";
        String encodedPassword = encoder.encode(repeatedPassword);
        assertThat(encodedPassword).isNotBlank();
        assertThat(encoder.matches(repeatedPassword, encodedPassword)).isTrue();
    }

    @Test
    void passwordEncoder_ShouldBeThreadSafe() throws InterruptedException {
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String password = "threadSafePassword";
        String[] encodedResults = new String[2];
        Thread thread1 = new Thread(() -> encodedResults[0] = encoder.encode(password));
        Thread thread2 = new Thread(() -> encodedResults[1] = encoder.encode(password));
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertThat(encodedResults[0]).isNotBlank();
        assertThat(encodedResults[1]).isNotBlank();
        assertThat(encodedResults[0]).isNotEqualTo(encodedResults[1]);
        assertThat(encoder.matches(password, encodedResults[0])).isTrue();
        assertThat(encoder.matches(password, encodedResults[1])).isTrue();
    }
}
