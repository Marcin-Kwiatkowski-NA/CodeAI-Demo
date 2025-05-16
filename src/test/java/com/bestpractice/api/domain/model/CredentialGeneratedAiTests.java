package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;

class CredentialGeneratedAiTests {

    private Credential credential;

    @BeforeEach
    void setUp() {
        // GIVEN: Create a new Credential object for each test.
        String token = "testToken";
        String tokenType = "Bearer";
        Date exp = new Date();
        boolean isRefresh = false;

        credential = new Credential(token, tokenType, exp, isRefresh);
    }

    @Test
    void constructor_validInput() {
        // GIVEN: Create a new Credential object with valid input values.
        // WHEN: The Credential object is constructed with the given values.
        // THEN: Verify that the Credential object's attributes are set correctly.
        assert credential.getToken() == token;
        assert credential.getTokenType() == tokenType;
        assert credential.getExp() == exp;
        assert credential.isRefresh() == isRefresh;
    }

    @Test
    void getToken_returnsToken() {
        // GIVEN: A Credential object is created.
        // WHEN: The getToken() method is called.
        // THEN: Verify that the returned token is the same as the token set during construction.
        String returnedToken = credential.getToken();
        assert returnedToken == token;
    }

    @Test
    void getTokenType_returnsTokenType() {
        // GIVEN: A Credential object is created.
        // WHEN: The getTokenType() method is called.
        // THEN: Verify that the returned token type is the same as the token type set during construction.
        String returnedTokenType = credential.getTokenType();
        assert returnedTokenType == tokenType;
    }

    @Test
    void getExp_returnsExp() {
        // GIVEN: A Credential object is created.
        // WHEN: The getExp() method is called.
        // THEN: Verify that the returned expiration date is the same as the expiration date set during construction.
        Date returnedExp = credential.getExp();
        assert returnedExp == exp;
    }

    @Test
    void isRefresh_returnsIsRefresh() {
        // GIVEN: A Credential object is created.
        // WHEN: The isRefresh() method is called.
        // THEN: Verify that the returned refresh flag is the same as the refresh flag set during construction.
        boolean returnedIsRefresh = credential.isRefresh();
        assert returnedIsRefresh == isRefresh;
    }
}
