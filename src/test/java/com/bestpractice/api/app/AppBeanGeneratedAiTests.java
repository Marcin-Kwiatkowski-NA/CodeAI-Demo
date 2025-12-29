package com.bestpractice.api.app;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;

    @Test
    void shouldThrowInternalServerErrorWhenHmacSecretIsMissing() {
        // GIVEN
        when(credentialProperty.getHmacSecret()).thenReturn(null);

        // WHEN & THEN
        assertThatThrownBy(() -> {
            authComponent.generateCredential("testSubject", "testProvider", "testKey", "testAlg", 2);
        }).isInstanceOf(InternalServerError.class)
          .hasMessageContaining("HMAC secret is missing");
    }
}
