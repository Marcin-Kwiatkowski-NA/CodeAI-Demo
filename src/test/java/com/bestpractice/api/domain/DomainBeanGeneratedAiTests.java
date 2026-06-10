package com.bestpractice.api.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        // GIVEN
        DomainBean bean = domainBean;

        // WHEN
        PasswordEncoder encoder = bean.passwordEncoder();

        // THEN
        assertNotNull(encoder);
        assertEquals(BCryptPasswordEncoder.class, encoder.getClass());
    }

    @Test
    void passwordEncoder_ShouldEncodePasswordCorrectly() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "securePassword";

        // WHEN
        String encodedPassword = encoder.encode(rawPassword);

        // THEN
        assertNotNull(encodedPassword);
        assertEquals(true, encoder.matches(rawPassword, encodedPassword));
    }

    @Test
    void passwordEncoder_ShouldNotMatchDifferentPasswords() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "securePassword";
        String differentPassword = "otherPassword";

        // WHEN
        String encodedPassword = encoder.encode(rawPassword);

        // THEN
        assertEquals(false, encoder.matches(differentPassword, encodedPassword));
    }

    @Test
    void passwordEncoder_ShouldHandleEmptyStringPassword() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "";

        // WHEN
        String encodedPassword = encoder.encode(rawPassword);

        // THEN
        assertNotNull(encodedPassword);
        assertEquals(true, encoder.matches(rawPassword, encodedPassword));
    }

    @Test
    void passwordEncoder_ShouldHandleWhitespaceOnlyPassword() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "   ";

        // WHEN
        String encodedPassword = encoder.encode(rawPassword);

        // THEN
        assertNotNull(encodedPassword);
        assertEquals(true, encoder.matches(rawPassword, encodedPassword));
    }

    @Test
    void passwordEncoder_ShouldHandleSingleCharacterPassword() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "a";

        // WHEN
        String encodedPassword = encoder.encode(rawPassword);

        // THEN
        assertNotNull(encodedPassword);
        assertEquals(true, encoder.matches(rawPassword, encodedPassword));
    }

    @Test
    void passwordEncoder_ShouldHandleLongPassword() {
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
        assertNotNull(encodedPassword);
        assertEquals(true, encoder.matches(rawPassword, encodedPassword));
    }

    @Test
    void passwordEncoder_ShouldHandleSpecialCharactersPassword() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "!@#$%^&*()_+-=[]{}|;':,.<>?/";

        // WHEN
        String encodedPassword = encoder.encode(rawPassword);

        // THEN
        assertNotNull(encodedPassword);
        assertEquals(true, encoder.matches(rawPassword, encodedPassword));
    }

    @Test
    void passwordEncoder_ShouldHandleNumericBoundaryValuesAsString() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String minValuePassword = String.valueOf(Integer.MIN_VALUE);
        String maxValuePassword = String.valueOf(Integer.MAX_VALUE);

        // WHEN
        String encodedMin = encoder.encode(minValuePassword);
        String encodedMax = encoder.encode(maxValuePassword);

        // THEN
        assertNotNull(encodedMin);
        assertNotNull(encodedMax);
        assertEquals(true, encoder.matches(minValuePassword, encodedMin));
        assertEquals(true, encoder.matches(maxValuePassword, encodedMax));
    }

    @Test
    void passwordEncoder_ShouldHandleUnicodeCharactersPassword() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "パスワード😊";

        // WHEN
        String encodedPassword = encoder.encode(rawPassword);

        // THEN
        assertNotNull(encodedPassword);
        assertEquals(true, encoder.matches(rawPassword, encodedPassword));
    }

    @Test
    void passwordEncoder_ShouldGenerateDifferentEncodingsForSamePassword() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "samePassword";

        // WHEN
        String encoded1 = encoder.encode(rawPassword);
        String encoded2 = encoder.encode(rawPassword);

        // THEN
        assertNotNull(encoded1);
        assertNotNull(encoded2);
        assertEquals(true, encoder.matches(rawPassword, encoded1));
        assertEquals(true, encoder.matches(rawPassword, encoded2));
        assertEquals(false, encoded1.equals(encoded2));
    }

    @Test
    void passwordEncoder_ShouldHandlePasswordWithLeadingAndTrailingSpaces() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "  password  ";

        // WHEN
        String encodedPassword = encoder.encode(rawPassword);

        // THEN
        assertNotNull(encodedPassword);
        assertEquals(true, encoder.matches(rawPassword, encodedPassword));
    }

    @Test
    void passwordEncoder_ShouldHandleMixedCasePassword() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "AbCdEfGhIjK";

        // WHEN
        String encodedPassword = encoder.encode(rawPassword);

        // THEN
        assertNotNull(encodedPassword);
        assertEquals(true, encoder.matches(rawPassword, encodedPassword));
    }

    @Test
    void passwordEncoder_ShouldHandleNumericStringPasswordZeroAndOne() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String zeroPassword = "0";
        String onePassword = "1";

        // WHEN
        String encodedZero = encoder.encode(zeroPassword);
        String encodedOne = encoder.encode(onePassword);

        // THEN
        assertNotNull(encodedZero);
        assertNotNull(encodedOne);
        assertEquals(true, encoder.matches(zeroPassword, encodedZero));
        assertEquals(true, encoder.matches(onePassword, encodedOne));
    }

    @Test
    void passwordEncoder_ShouldHandleReversedStringPassword() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "abcdef";
        String reversedPassword = new StringBuilder(rawPassword).reverse().toString();

        // WHEN
        String encodedRaw = encoder.encode(rawPassword);
        String encodedReversed = encoder.encode(reversedPassword);

        // THEN
        assertNotNull(encodedRaw);
        assertNotNull(encodedReversed);
        assertEquals(false, encoder.matches(rawPassword, encodedReversed));
        assertEquals(true, encoder.matches(reversedPassword, encodedReversed));
    }

    @Test
    void passwordEncoder_ShouldHandleDuplicateCharacterPassword() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "aaaaaa";

        // WHEN
        String encodedPassword = encoder.encode(rawPassword);

        // THEN
        assertNotNull(encodedPassword);
        assertEquals(true, encoder.matches(rawPassword, encodedPassword));
    }

    @Test
    void passwordEncoder_ShouldProduceDifferentEncodingsForDifferentPasswords() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String password1 = "passwordOne";
        String password2 = "passwordTwo";

        // WHEN
        String encoded1 = encoder.encode(password1);
        String encoded2 = encoder.encode(password2);

        // THEN
        assertNotNull(encoded1);
        assertNotNull(encoded2);
        assertEquals(false, encoder.matches(password1, encoded2));
        assertEquals(false, encoded1.equals(encoded2));
    }

    @Test
    void passwordEncoder_ShouldHandleVeryLongWhitespacePassword() {
        // GIVEN
        PasswordEncoder encoder = domainBean.passwordEncoder();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            sb.append(" ");
        }
        String rawPassword = sb.toString();

        // WHEN
        String encodedPassword = encoder.encode(rawPassword);

        // THEN
        assertNotNull(encodedPassword);
        assertEquals(true, encoder.matches(rawPassword, encodedPassword));
    }
}
