package com. bestpractice. api. domain. component;

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


import com. auth0. jwt. JWT;


import com. auth0. jwt. algorithms. Algorithm;


import com. auth0. jwt. exceptions. JWTVerificationException;


import com. auth0. jwt. interfaces. DecodedJWT;


import com. bestpractice. api. common. exception. InternalServerError;


import com. bestpractice. api. common. exception. Unauthorized;


import com. bestpractice. api. common. property. CredentialProperty;


import com. bestpractice. api. domain. model. Credential;


import org. junit. jupiter. api. BeforeEach;


import org. junit. jupiter. api. Test;


import org. junit. jupiter. api. extension. ExtendWith;


import org. mockito. InjectMocks;


import org. mockito. Mock;


import org. mockito. junit. jupiter. MockitoExtension;


import java. util. Date;


import static org. junit. jupiter. api. Assertions.*;


import static org. mockito. Mockito.*;


@ExtendWith( MockitoExtension. class)


public class AuthComponentGeneratedAiTests {


    @Mock


    private CredentialProperty credentialProperty;


    @InjectMocks


    private AuthComponent authComponent;


    @BeforeEach


    void setUp() {


        when(credentialProperty. getSecretKey()). thenReturn("test- secret- key");


        when(credentialProperty. getIssuer()). thenReturn("test- issuer");


        when(credentialProperty. getExpirationTime()). thenReturn(36 00L);


    }


    @Test


    void testGenerateToken() {


        String userId = "test- user";


        String email = "test@ example. com";


        Credential credential = authComponent. generateToken( userId, email, false);


        assertNotNull(credential);


        assertNotNull(credential. getToken());


        assertTrue(credential. getToken(). startsWith("Bearer "));


        assertNotNull(credential. getExpiresAt());


        assertFalse(credential. isRefresh());


    }


    @Test


    void testGenerateRefreshToken() {


        String userId = "test- user";


        String email = "test@ example. com";


        Credential credential = authComponent. generateToken( userId, email, true);


        assertNotNull(credential);


        assertNotNull(credential. getToken());


        assertTrue(credential. getToken(). startsWith("Bearer "));


        assertNotNull(credential. getExpiresAt());


        assertTrue(credential. isRefresh());


    }


    @Test


    void testVerifyToken() {


        String userId = "test- user";


        String email = "test@ example. com";


        Credential credential = authComponent. generateToken( userId, email, false);


        DecodedJWT decodedJWT = authComponent. verifyToken(credential. getToken());


        assertNotNull(decodedJWT);


        assertEquals(userId, decodedJWT. getClaim("sub"). asString());


        assertEquals(email, decodedJWT. getClaim("email"). asString());


    }


    @Test


    void testVerifyInvalidToken() {


        assertThrows(Unauthorized. class, () -> {


            authComponent. verifyToken("invalid- token");


        });


    }


}
