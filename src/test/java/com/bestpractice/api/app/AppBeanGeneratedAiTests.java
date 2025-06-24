package com.bestpractice.api.app;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.common.property.CredentialProperty;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import com.bestpractice.api.domain.model.Credential;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AppBeanGeneratedAiTests {

  private AppBean appBean;
  private AuthComponent authComponent;
  private RequestInfoComponent requestInfo;
  private CredentialProperty credentialProperty;

  @BeforeEach
  void setUp() {
    credentialProperty = new CredentialProperty();
    authComponent = new AuthComponent(credentialProperty);
    requestInfo = new RequestInfoComponent();
    appBean = new AppBean();
  }

  @Test
  void testDecodeJwt_validToken() {
    // GIVEN
    String validToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ0.eycJmV1dCBsb25jZSJfdQ.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";

    // WHEN
    DecodedJWT decodedJwt = authComponent.decodeJwt(validToken);

    // THEN
    assert decodedJwt != null;
  }
}
