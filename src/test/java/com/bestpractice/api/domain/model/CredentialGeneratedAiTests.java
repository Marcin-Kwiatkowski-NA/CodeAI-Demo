package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;

public class CredentialGeneratedAiTests {

    @Test
    public void constructor_validInput_createsCredential() {
        // GIVEN: Valid input values
        String token = "testToken";
        String tokenType = "Bearer";
        Date exp = new Date();
        boolean isRefresh = false;

        // WHEN: Credential is created
        Credential credential = new Credential(token, tokenType, exp, isRefresh);

        // THEN: Assert that the credential is created correctly
        assert credential.getToken() == token;
        assert credential.getTokenType() == tokenType;
        assert credential.getExp() == exp;
        assert credential.isRefresh() == isRefresh;
    }

    @Test
    public void getToken_returnsToken() {
        // GIVEN: A Credential object
        String token = "testToken";
        String tokenType = "Bearer";
        Date exp = new Date();
        boolean isRefresh = false;
        Credential credential = new Credential(token, tokenType, exp, isRefresh);

        // WHEN: getToken() method is called
        String returnedToken = credential.getToken();

        // THEN: Assert that the returned token is the same as the stored token
        assert returnedToken == token;
    }

    @Test
    public void getTokenType_returnsTokenType() {
        // GIVEN: A Credential object
        String token = "testToken";
        String tokenType = "Bearer";
        Date exp = new Date();
        boolean isRefresh = false;
        Credential credential = new Credential(token, tokenType, exp, isRefresh);

        // WHEN: getTokenType() method is called
        String returnedTokenType = credential.getTokenType();

        // THEN: Assert that the returned token type is the same as the stored token type
        assert returnedTokenType == tokenType;
    }

    @Test
    public void getExp_returnsExp() {
        // GIVEN: A Credential object
        String token = "testToken";
        String tokenType = "Bearer";
        Date exp = new Date();
        boolean isRefresh = false;
        Credential credential = new Credential(token, tokenType, exp, isRefresh);

        // WHEN: getExp() method is called
        Date returnedExp = credential.getExp();

        // THEN: Assert that the returned expiration date is the same as the stored expiration date
        assert returnedExp == exp;
    }

    @Test
    public void isRefresh_returnsIsRefresh() {
        // GIVEN: A Credential object
        String token = "testToken";
        String tokenType = "Bearer";
        Date exp = new Date();
        boolean isRefresh = false;
        Credential credential = new Credential(token, tokenType, exp, isRefresh);

        // WHEN: isRefresh() method is called
        boolean returnedIsRefresh = credential.isRefresh();

        // THEN: Assert that the returned refresh flag is the same as the stored refresh flag
        assert returnedIsRefresh == isRefresh;
    }
}
