package com.bestpractice.api.domain.component;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org. junit. jupiter. api. BeforeEach;
import org. junit. jupiter. api. Test;
import org. junit. jupiter. api. extension. ExtendWith;
import org. mockito. InjectMocks;
import org. mockito. Mock;
import org. mockito. junit. jupiter. MockitoExtension;
import org. springframework. security. crypto. bcrypt. BCryptPasswordEncoder;
import org. springframework. security. crypto. password. PasswordEncoder;

import static org. assertj. core. api. Assertions. assertThat;

@ExtendWith( MockitoExtension. class)
public class BCryptPasswordEncryptionComponentGeneratedAiTests {

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private BCryptPasswordEncryptionComponent bCryptPasswordEncryptionComponent;

    @BeforeEach
    void setUp() {
        bCryptPasswordEncryptionComponent = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void encodePassword_ shouldReturnEncodedPassword() {
        // GIVEN
        String rawPassword = "password123";

        // WHEN
        String encodedPassword = bCryptPasswordEncryptionComponent. encodePassword( rawPassword);

        // THEN
        assertThat( encodedPassword). isNotNull();
        assertThat( encodedPassword). isNotEqualTo( rawPassword);
    }

    @Test
    void matchedPassword_ shouldReturnTrueForMatchingPassword() {
        // GIVEN
        String rawPassword = "password123";
        String encodedPassword = bCryptPasswordEncryptionComponent. encodePassword( rawPassword);

        // WHEN
        boolean isMatched = bCryptPasswordEncryptionComponent. matchedPassword( rawPassword, encodedPassword);

        // THEN
        assertThat( isMatched). isTrue();
    }

    @Test
    void matchedPassword_ shouldReturnFalseForNonMatchingPassword() {
        // GIVEN
        String rawPassword = "password123";
        String encodedPassword = bCryptPasswordEncryptionComponent. encodePassword( rawPassword);
        String differentPassword = "differentPassword";

        // WHEN
        boolean isMatched = bCryptPasswordEncryptionComponent. matchedPassword( differentPassword, encodedPassword);

        // THEN
        assertThat( isMatched). isFalse();
    }
}
