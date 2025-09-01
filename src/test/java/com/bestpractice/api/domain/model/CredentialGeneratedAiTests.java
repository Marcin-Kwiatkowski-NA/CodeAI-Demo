package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CredentialGeneratedAiTests {

    private Credential credential;

    @BeforeEach
    void setUp() {
        credential = new Credential("testToken", "Bearer", new Date(), false);
    }

    @Test
    void test getToken() {
        assertEquals("testToken", credential.getToken());
    }

    @Test
    void test getTokenType() {
        assertEquals("Bearer", credential.getTokenType());
    }

    @Test
    void test getExp() {
        assertEquals(new Date(), credential.getExp());
    }

    @Test
    void test isRefresh() {
        assertEquals(false, credential.isRefresh());
    }
}
