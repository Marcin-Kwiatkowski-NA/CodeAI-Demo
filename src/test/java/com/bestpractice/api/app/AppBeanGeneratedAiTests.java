package com.bestpractice.api.app;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.Mockito;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.HandlerInterceptor;
import springfox.documentation.spring.web.plugins.Docket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@ExtendWith(MockitoExtension.class)
class AppBeanGeneratedAiTests {

    @Mock
    private AuthComponent authComponent;

    @Mock
    private RequestInfoComponent requestInfo;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private InterceptorController interceptorController;

    private AppBean.SwaggerConfig swaggerConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        swaggerConfig = new AppBean.SwaggerConfig();
    }

    @Test
    void givenValidRequest_whenPreHandle_thenReturnsTrue() throws Exception {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/resource");
        when(request.getMethod()).thenReturn("GET");
        Enumeration<String> authorizationHeader = mock(Enumeration.class);
        when(request.getHeaders("Authorization")).thenReturn(authorizationHeader);
        when(authorizationHeader.hasMoreElements()).thenReturn(true);
        when(authorizationHeader.nextElement()).thenReturn("Bearer validToken");
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(authComponent.decodeJwt("validToken")).thenReturn(decodedJWT);
        when(decodedJWT.getSubject()).thenReturn("user123");
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn("user@example.com");
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(false);

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        assertThat(result).isTrue();
        verify(requestInfo).setRequestId(anyString());
        verify(requestInfo).setPath("/api/v1/resource");
        verify(requestInfo).setHttpMethod("GET");
        verify(requestInfo).setUserId("user123");
        verify(requestInfo).setUserEmail("user@example.com");
        verify(requestInfo).setRefreshToken(false);
    }

    @Test
    void givenInvalidAuthorizationHeader_whenPreHandle_thenThrowsUnAuthorized() {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/resource");
        when(request.getMethod()).thenReturn("GET");
        Enumeration<String> authorizationHeader = mock(Enumeration.class);
        when(request.getHeaders("Authorization")).thenReturn(authorizationHeader);
        when(authorizationHeader.hasMoreElements()).thenReturn(false);

        // WHEN
        Throwable thrown = catchThrowable(() -> interceptorController.preHandle(request, response, new Object()));

        // THEN
        assertThat(thrown).isInstanceOf(UnAuthorized.class).hasMessage("Authorization header is empty");
    }

    @Test
    void givenSwaggerConfig_whenSwaggerSpringMvcPlugin_thenReturnsDocket() {
        // GIVEN

        // WHEN
        Docket docket = swaggerConfig.swaggerSpringMvcPlugin();

        // THEN
        assertThat(docket).isNotNull();
        assertThat(docket.getDocumentationType()).isEqualTo(springfox.documentation.spi.DocumentationType.SWAGGER_2);
    }
}