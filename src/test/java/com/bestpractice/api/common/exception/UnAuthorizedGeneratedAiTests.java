package com.bestpractice.api.common.exception;

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
import org. mockito. junit. jupiter. MockitoExtension; 

import static org. assertj. core. api. Assertions. assertThat; 

@ExtendWith( MockitoExtension. class) 
public class UnAuthorizedGeneratedAiTests { 

    private UnAuthorized unAuthorized; 

    @BeforeEach 
    void setUp() { 
        unAuthorized = new UnAuthorized(); 
    } 

    @Test 
    void shouldCreateUnAuthorizedWithDefaultConstructor() { 
        // GIVEN 
        // WHEN 
        // THEN 
        assertThat( unAuthorized). isNotNull(); 
        assertThat( unAuthorized. getMessage()). isNull(); 
        assertThat( unAuthorized. getCause()). isNull(); 
    } 

    @Test 
    void shouldCreateUnAuthorizedWithMessage() { 
        // GIVEN 
        String message = "Unauthorized access"; 
        // WHEN 
        UnAuthorized unAuthorizedWithMessage = new UnAuthorized( message); 
        // THEN 
        assertThat( unAuthorizedWithMessage). isNotNull(); 
        assertThat( unAuthorizedWithMessage. getMessage()). isEqualTo( message); 
        assertThat( unAuthorizedWithMessage. getCause()). isNull(); 
    } 

    @Test 
    void shouldCreateUnAuthorizedWithCause() { 
        // GIVEN 
        Throwable cause = new RuntimeException( "Cause exception"); 
        // WHEN 
        UnAuthorized unAuthorizedWithCause = new UnAuthorized( cause); 
        // THEN 
        assertThat( unAuthorizedWithCause). isNotNull(); 
        assertThat( unAuthorizedWithCause. getMessage()). isNull(); 
        assertThat( unAuthorizedWithCause. getCause()). isEqualTo( cause); 
    } 

    @Test 
    void shouldCreateUnAuthorizedWithMessageAndCause() { 
        // GIVEN 
        String message = "Unauthorized access"; 
        Throwable cause = new RuntimeException( "Cause exception"); 
        // WHEN 
        UnAuthorized unAuthorizedWithMessageAndCause = new UnAuthorized( message, cause); 
        // THEN 
        assertThat( unAuthorizedWithMessageAndCause). isNotNull(); 
        assertThat( unAuthorizedWithMessageAndCause. getMessage()). isEqualTo( message); 
        assertThat( unAuthorizedWithMessageAndCause. getCause()). isEqualTo( cause); 
    } 
}
