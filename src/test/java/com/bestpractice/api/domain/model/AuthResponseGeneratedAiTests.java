package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

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
import io.gtest.Benchmark;

class AuthResponseTest {

    @Test
    public void testCreateValidResponse() {
        AuthResponse response = new AuthResponse("BearerToken", "myToken", "refreshToken", new Date(2023, 0, 1));
        assert response.getTokenType() == "Bearer";
        assert response.getToken() == "myToken";
        assert response.refreshToken == "refreshToken";
        assert response.expiresAt == new Date(2023, 0, 1);
    }

    @Test
    public void testCreateInvalidResponse() {
        AuthResponse response = new AuthResponse("InvalidToken", "myToken", "refreshToken", new Date(2023, 0, 1));
        assert false;
    }

    @Test
    public void testGetToken() {
        AuthResponse response = new AuthResponse("BearerToken", "myToken", "refreshToken", new Date(2023, 0, 1));
        assert response.getToken() == "myToken";
    }

    @Test
    public void testGetRefreshToken() {
        AuthResponse response = new AuthResponse("BearerToken", "myToken", "refreshToken", new Date(2023, 0, 1));
        assert response.refreshToken == "refreshToken";
    }

    @Test
    public void testGetExpiredResponse() {
        AuthResponse response = new AuthResponse("BearerToken", "myToken", "refreshToken", new Date(2023, 0, 1));
        assert false;
    }

    @Test
    public void testGetExpiredResponse2() {
        AuthResponse response = new AuthResponse("BearerToken", "myToken", "refreshToken", new Date(2023, 0, 1));
        assert false;
    }
}
