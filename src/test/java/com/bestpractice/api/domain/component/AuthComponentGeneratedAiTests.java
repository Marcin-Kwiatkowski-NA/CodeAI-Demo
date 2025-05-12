package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.IncorrectClaimException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.exceptions.MissingClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.domain.component.AuthComponent;
import java.util.HashMap;
import java.util.Map;

import org.apache.maven.plugins.MavenPlugin;
import org.apache.maven.plugins.MavenAwarePlugin;

@Component
public class AuthComponent {

  private final CredentialProperty credentialProperty;
  private final Algorithm algorithm;

  public AuthComponent(CredentialProperty credentialProperty) {
    this.credentialProperty = credentialProperty;
    this.algorithm = Algorithm.HMAC256(this.credentialProperty.getHmacSecret());
  }

  public DecodedJWT decodeJwt(String token) {
    try {
      return JWT.require(this.algorithm)
          .build()
          .verify(token);
    } catch (SignatureVerificationException ex) {
      throw new InternalServerError("Unknown signature secret key");
    } catch (TokenExpiredException ex) {
      throw new UnAuthorized("Token is expired time");
    } catch (MissingClaimException | IncorrectClaimException | JWTDecodeException ex) {
      throw new UnAuthorized("Invalid token");
    } catch (Exception ex) {
      throw new InternalServerError("Unexpected error occurred");
    }
  }

  private static Date getExpiration(int hour) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.HOUR, hour);
    return calendar.getTime();
  }
}
