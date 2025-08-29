package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class CredentialGeneratedAiTests {

    private Credential credential;

    @BeforeEach
    void setUp() {
        credential = new Credential("token123", "Bearer", new Date(), false);
    }

    @Test
    void testGetToken() {
        // GIVEN a Credential object with a token value
        // WHEN the getToken() method is called
        // THEN the token value ("token123") should be returned
        assertEquals("token123", credential.getToken());
    }

    @Test
    void testGetTokenType() {
        // GIVEN a Credential object with a token type
        // WHEN the getTokenType() method is called
        // THEN the token type ("Bearer") should be returned
        assertEquals("Bearer", credential.getTokenType());
    }

    @Test
    void testGetExp() {
        // GIVEN a Credential object with an expiration date
        // WHEN the getExp() method is called
        // THEN the expiration date should be returned
        assertEquals(new Date(), credential.getExp());
    }

    @Test
    void testIsRefresh() {
        // GIVEN a Credential object with a refresh flag set to false
        // WHEN the isRefresh() method is called
        // THEN the refresh flag (false) should be returned
        assertEquals(false, credential.isRefresh());
    }
}
