package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class BadRequestGeneratedAiTests {

    @Test
    void shouldNotAllowNullMessageInConstructor() {
        // GIVEN
        String nullMessage = null;

        // WHEN & THEN
        assertThatThrownBy(() -> new BadRequest(nullMessage))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("null");
    }

    @Test
    void shouldNotAllowNullCauseInConstructor() {
        // GIVEN
        Throwable nullCause = null;

        // WHEN & THEN
        assertThatThrownBy(() -> new BadRequest("Error message", nullCause))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Error message");
    }
}
