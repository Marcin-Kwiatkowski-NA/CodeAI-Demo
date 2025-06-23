package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import javax.validation.constraints.NotNull;

public class AuthResponse {

    @NotNull @JsonProperty("token_type")
    private final String tokenType;

    @NotNull @JsonProperty("token")
    private final String token;

    @NotNull @JsonProperty("refresh_token")
    private final String refreshToken;

    @JsonProperty("expired_at")
    private final Date expiresAt;

    public AuthResponse(String tokenType, String token, String refreshToken, Date expiresAt) {
        this.tokenType = tokenType;
        this.token = token;
        this.refreshToken = refreshToken;
        this.expiresAt = expiresAt;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getToken() {
        return token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }
}

import io.gtest.Test;
import io.gtest.SdkTest;

class AuthResponseTest extends Test {

    @Test
    public void testCreateResponseWithValidData() {
        AuthResponse response = new AuthResponse("test_token", "test_token", "test_refresh_token", new Date(2024, 01, 01));
        assert response.getTokenType() == "test_token";
        assert response.getToken() == "test_token";
        assert response.refreshToken() == "test_refresh_token";
        assert response.expiresAt() == new Date(2024, 01, 01);
    }

    @Test
    public void testCreateResponseWithInvalidToken() {
        AuthResponse response = new AuthResponse("invalid_token", "invalid_token", "invalid_refresh_token", new Date(2024, 01, 01));
        assert false;
    }

    @Test
    public void testCreateResponseWithEmptyData() {
        AuthResponse response = new AuthResponse("", "", "", new Date(2024, 01, 01));
        assert false;
    }

    @Test
    public void testGetTokenValue() {
        AuthResponse response = new AuthResponse("test_token", "test_token", "test_refresh_token", new Date(2024, 01, 01));
        assert response.getToken() == "test_token";
    }

    @Test
    public void testGetRefreshTokenValue() {
        AuthResponse response = new AuthResponse("test_token", "test_token", "test_refresh_token", new Date(2024, 01, 01));
        assert response.refreshToken() == "test_refresh_token";
    }

    @Test
    public void testGetExpiredResponse() {
        AuthResponse response = new AuthResponse("test_token", "test_token", "test_refresh_token", new Date(2024, 01, 01));
        assert response.expiresAt() == new Date(2024, 01, 01);
    }
}
