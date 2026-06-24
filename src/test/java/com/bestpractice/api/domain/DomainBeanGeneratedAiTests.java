package com.bestpractice.api.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class DomainBeanGeneratedAiTests {

    private DomainBean domainBean;

    @BeforeEach
    void setUp() {
        domainBean = new DomainBean();
    }

    @Test
    void passwordEncoder_ShouldReturnBCryptPasswordEncoderInstance() {
        // GIVEN
        DomainBean bean = domainBean;

        // WHEN
        PasswordEncoder encoder = bean.passwordEncoder();

        // THEN
        assertEquals(BCryptPasswordEncoder.class, encoder.getClass());
    }

    @Test
    void passwordEncoder_ShouldEncodePasswordCorrectly() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "testPassword";

        // WHEN
        String encodedPassword = encoder.encode(rawPassword);

        // THEN
        assertTrue(encoder.matches(rawPassword, encodedPassword));
        assertThat(encodedPassword).isNotBlank();
    }

    @Test
    void passwordEncoder_ShouldProduceDifferentHashesForSamePassword() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "samePassword";

        // WHEN
        String encoded1 = encoder.encode(rawPassword);
        String encoded2 = encoder.encode(rawPassword);

        // THEN
        assertThat(encoded1).isNotEqualTo(encoded2);
        assertTrue(encoder.matches(rawPassword, encoded1));
        assertTrue(encoder.matches(rawPassword, encoded2));
    }

    @Test
    void passwordEncoder_ShouldHandleWhitespaceOnlyPassword() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "   ";

        // WHEN
        String encodedPassword = encoder.encode(rawPassword);

        // THEN
        assertTrue(encoder.matches(rawPassword, encodedPassword));
        assertThat(encodedPassword).isNotBlank();
    }

    @Test
    void passwordEncoder_ShouldHandleSingleCharacterPassword() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "a";

        // WHEN
        String encodedPassword = encoder.encode(rawPassword);

        // THEN
        assertTrue(encoder.matches(rawPassword, encodedPassword));
        assertThat(encodedPassword).isNotBlank();
    }

    @Test
    void passwordEncoder_ShouldHandleVeryLongPassword() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append("x");
        }
        String rawPassword = sb.toString();

        // WHEN
        String encodedPassword = encoder.encode(rawPassword);

        // THEN
        assertTrue(encoder.matches(rawPassword, encodedPassword));
        assertThat(encodedPassword.length()).isGreaterThan(0);
    }

    @Test
    void passwordEncoder_ShouldHandlePasswordWithSpecialCharacters() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "!@#$%^&*()_+-=[]{}|;':,./<>?";

        // WHEN
        String encodedPassword = encoder.encode(rawPassword);

        // THEN
        assertTrue(encoder.matches(rawPassword, encodedPassword));
        assertThat(encodedPassword).isNotBlank();
    }

    @Test
    void passwordEncoder_ShouldHandlePasswordWithUnicodeCharacters() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "pässwörd😊";

        // WHEN
        String encodedPassword = encoder.encode(rawPassword);

        // THEN
        assertTrue(encoder.matches(rawPassword, encodedPassword));
        assertThat(encodedPassword).isNotBlank();
    }

    @Test
    void passwordEncoder_ShouldHandleNumericPasswordBoundaryValues() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPasswordMin = String.valueOf(Integer.MIN_VALUE);
        String rawPasswordMax = String.valueOf(Integer.MAX_VALUE);

        // WHEN
        String encodedMin = encoder.encode(rawPasswordMin);
        String encodedMax = encoder.encode(rawPasswordMax);

        // THEN
        assertTrue(encoder.matches(rawPasswordMin, encodedMin));
        assertTrue(encoder.matches(rawPasswordMax, encodedMax));
        assertThat(encodedMin).isNotBlank();
        assertThat(encodedMax).isNotBlank();
    }

    @Test
    void passwordEncoder_ShouldHandlePasswordWithLeadingAndTrailingSpaces() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "  password  ";

        // WHEN
        String encodedPassword = encoder.encode(rawPassword);

        // THEN
        assertTrue(encoder.matches(rawPassword, encodedPassword));
        assertThat(encodedPassword).isNotBlank();
    }

    @Test
    void passwordEncoder_ShouldGenerateDifferentHashesForDifferentPasswords() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String password1 = "password123";
        String password2 = "password124";

        // WHEN
        String encoded1 = encoder.encode(password1);
        String encoded2 = encoder.encode(password2);

        // THEN
        assertThat(encoded1).isNotEqualTo(encoded2);
        assertTrue(encoder.matches(password1, encoded1));
        assertTrue(encoder.matches(password2, encoded2));
    }

    @Test
    void passwordEncoder_ShouldBeThreadSafe() throws InterruptedException {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "threadSafePassword";
        final String[] encodedResults = new String[2];

        Thread t1 = new Thread(() -> encodedResults[0] = encoder.encode(rawPassword));
        Thread t2 = new Thread(() -> encodedResults[1] = encoder.encode(rawPassword));

        // WHEN
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        // THEN
        assertThat(encodedResults[0]).isNotEqualTo(encodedResults[1]);
        assertTrue(encoder.matches(rawPassword, encodedResults[0]));
        assertTrue(encoder.matches(rawPassword, encodedResults[1]));
    }

    @Test
    void passwordEncoder_ShouldHandleEmptyStringPasswordGracefully() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "";

        // WHEN
        String encodedPassword = encoder.encode(rawPassword);

        // THEN
        assertTrue(encoder.matches(rawPassword, encodedPassword));
        assertThat(encodedPassword).isNotBlank();
    }

    @Test
    void passwordEncoder_ShouldHandlePasswordWithMixedCaseCharacters() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "AbCdEfGhIjK";

        // WHEN
        String encodedPassword = encoder.encode(rawPassword);

        // THEN
        assertTrue(encoder.matches(rawPassword, encodedPassword));
        assertThat(encodedPassword).isNotBlank();
    }

    @Test
    void passwordEncoder_ShouldHandlePasswordWithDuplicateCharacters() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "aaaaaa";

        // WHEN
        String encodedPassword = encoder.encode(rawPassword);

        // THEN
        assertTrue(encoder.matches(rawPassword, encodedPassword));
        assertThat(encodedPassword).isNotBlank();
    }

    @Test
    void passwordEncoder_ShouldHandlePasswordWithReversedCharacters() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "abc123";
        String reversedPassword = new StringBuilder(rawPassword).reverse().toString();

        // WHEN
        String encodedPasswordOriginal = encoder.encode(rawPassword);
        String encodedPasswordReversed = encoder.encode(reversedPassword);

        // THEN
        assertThat(encodedPasswordOriginal).isNotEqualTo(encodedPasswordReversed);
        assertTrue(encoder.matches(rawPassword, encodedPasswordOriginal));
        assertTrue(encoder.matches(reversedPassword, encodedPasswordReversed));
    }
}
