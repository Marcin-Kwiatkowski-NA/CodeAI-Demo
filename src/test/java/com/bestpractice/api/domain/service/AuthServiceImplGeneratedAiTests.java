package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import com.auth0.jwt.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.common.property.CredentialProperty;
import com.bestpractice.api.domain.model.Credential;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.HashMap;
import java.util.Map;

public class AuthServiceImpl {

    @Override
    public AuthResponse login(String email, String password) {
        User user = this.userPersistentRepository.findByEmail(email);
        if (user == null) {
            throw new UnAuthorized("Email or password is invalid");
        }

        if (!this.encryptionComponent.matchedPassword(password, user.getPassword())) {
            throw new UnAuthorized("Email or password is invalid");
        }

        Credential token = this.authComponent.generateJwt(user.getId(), user.getEmail(), false);
        Credential refreshToken = this.authComponent.generateJwt(user.getId(), user.getEmail(), true);

        return new AuthResponse(token.getTokenType(), token.getToken(), refreshToken.getToken(), token.getExp());
    }
}
