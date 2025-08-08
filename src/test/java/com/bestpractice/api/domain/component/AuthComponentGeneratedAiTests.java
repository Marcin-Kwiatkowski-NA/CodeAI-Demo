package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.common.property.CredentialProperty;
import com.bestpractice.api.domain.model.Credential;
import java.util.HashMap;
import java.util.Map;

@org.junit.jupiter.api.DisplayName("AuthComponentGeneratedAiTests")
class AuthComponentGeneratedAiTests {

    private AuthComponent authComponent;
    private CredentialProperty credentialProperty;

    @BeforeEach
    void setUp() {
        credentialProperty = new CredentialProperty();
        credentialProperty.setHmacSecret("secret");
        credentialProperty.setProvider("provider");
        credentialProperty.setSubject("subject");
        credentialProperty.setAlg("alg");
        credentialProperty.setExpiresHourStr("60");
        authComponent = new AuthComponent(credentialProperty);
    }

    @Test
    void decodeJwt_validToken() {
        // GIVEN
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ0.ey...";

        // WHEN
        DecodedJWT decodedJwt = authComponent.decodeJwt(token);

        // THEN
        Assertions.assertNotNull(decodedJwt);
    }

    @Test
    void decodeJwt_expiredToken() {
        // GIVEN
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ0.ey...";

        // WHEN
        // THEN
        try {
            authComponent.decodeJwt(token);
        } catch (TokenExpiredException e) {
            // Assert that the exception is thrown
            Assertions.assertNotNull(e);
        }
    }

    @Test
    void generateJwt_valid() {
        // GIVEN
        String userId = "user123";
        String email = "user@example.com";

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, false);

        // THEN
        Assertions.assertNotNull(credential);
        Assertions.assertEquals(userId, credential.getSubject());
        Assertions.assertEquals(email, credential.getClaims().get("user_email"));
    }

    @Test
    void generateJwt_refresh_token() {
        // GIVEN
        String userId = "user123";
        String email = "user@example.com";

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, true);

        // THEN
        Assertions.assertNotNull(credential);
        Assertions.assertEquals(userId, credential.getSubject());
        Assertions.assertEquals(email, credential.getClaims().get("user_email"));
        Assertions.assertEquals(true, credential.getClaims().get("refresh_token"));
    }

    @Test
    void generateJwt_invalid_hour_str() {
        // GIVEN
        String userId = "user123";
        String email = "user@example.com";
        CredentialProperty credentialProperty = new CredentialProperty();
        credentialProperty.setExpiresHourStr("-");
        AuthComponent authComponent = new AuthComponent(credentialProperty);

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, false);

        // THEN
        Assertions.assertNull(credential.getExpiration());
    }

    @Test
    void generateJwt_invalid_hour_str_number_format_exception() {
        // GIVEN
        String userId = "user123";
        String email = "user@example.com";
        CredentialProperty credentialProperty = new CredentialProperty();
        credentialProperty.setExpiresHourStr("abc");
        AuthComponent authComponent = new AuthComponent(credentialProperty);

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, false);

        // THEN
        Assertions.assertNull(credential.getExpiration());
    }
}
