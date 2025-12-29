package com. bestpractice. api. common. exception;

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
import static org. junit. jupiter. api. Assertions. *;

public class BadRequestGeneratedAiTests {

    private BadRequest badRequest;

    @BeforeEach
    void setUp() {
        badRequest = new BadRequest();
    }

    @Test
    void shouldCreateBadRequestWithNoArguments() {
        // GIVEN
        // WHEN
        // THEN
        assertNotNull( badRequest );
    }

    @Test
    void shouldCreateBadRequestWithMessage() {
        // GIVEN
        String message = "Bad Request";
        // WHEN
        BadRequest badRequestWithMessage = new BadRequest( message );
        // THEN
        assertNotNull( badRequestWithMessage );
        assertEquals( message, badRequestWithMessage. getMessage() );
    }

    @Test
    void shouldCreateBadRequestWithCause() {
        // GIVEN
        Throwable cause = new RuntimeException( "Cause" );
        // WHEN
        BadRequest badRequestWithCause = new BadRequest( cause );
        // THEN
        assertNotNull( badRequestWithCause );
        assertEquals( cause, badRequestWithCause. getCause() );
    }

    @Test
    void shouldCreateBadRequestWithMessageAndCause() {
        // GIVEN
        String message = "Bad Request";
        Throwable cause = new RuntimeException( "Cause" );
        // WHEN
        BadRequest badRequestWithMessageAndCause = new BadRequest( message, cause );
        // THEN
        assertNotNull( badRequestWithMessageAndCause );
        assertEquals( message, badRequestWithMessageAndCause. getMessage() );
        assertEquals( cause, badRequestWithMessageAndCause. getCause() );
    }
}
