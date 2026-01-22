package com.bestpractice.api.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.assertj.core.api.Assertions;

@ExtendWith(MockitoExtension.class)
class AppBeanGeneratedAiTests {

    @Mock
    private com.bestpractice.api.domain.component.AuthComponent authComponent;

    @Mock
    private com.bestpractice.api.domain.component.InterceptorController interceptorController;

    @Mock
    private com.bestpractice.api.domain.component.RequestInfoComponent requestInfoComponent;

    private com.bestpractice.api.app.AppBean appBean;

    @BeforeEach
    void setUp() {
        appBean = new com.bestpractice.api.app.AppBean(authComponent, interceptorController, requestInfoComponent);
    }

    @Test
    void constructorInitializesDependencies() {
        // GIVEN: mocks are injected into the constructor
        // WHEN: the AppBean is instantiated
        // THEN: the injected dependencies are stored correctly
        Assertions.assertThat(appBean.getAuthComponent()).isSameAs(authComponent);
        Assertions.assertThat(appBean.getInterceptorController()).isSameAs(interceptorController);
        Assertions.assertThat(appBean.getRequestInfoComponent()).isSameAs(requestInfoComponent);
    }

    @Test
    void gettersReturnNonNullDependencies() {
        // GIVEN: an AppBean instance with mocked dependencies
        // WHEN: getters are called
        // THEN: the returned objects are not null
        Assertions.assertThat(appBean.getAuthComponent()).isNotNull();
        Assertions.assertThat(appBean.getInterceptorController()).isNotNull();
        Assertions.assertThat(appBean.getRequestInfoComponent()).isNotNull();
    }

    @Test
    void constructorThrowsIfAuthComponentIsNull() {
        // GIVEN: a null AuthComponent
        // WHEN: constructing AppBean with null AuthComponent
        // THEN: a NullPointerException is thrown
        Assertions.assertThatThrownBy(() -> new com.bestpractice.api.app.AppBean(null, interceptorController, requestInfoComponent))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void constructorThrowsIfInterceptorControllerIsNull() {
        // GIVEN: a null InterceptorController
        // WHEN: constructing AppBean with null InterceptorController
        // THEN: a NullPointerException is thrown
        Assertions.assertThatThrownBy(() -> new com.bestpractice.api.app.AppBean(authComponent, null, requestInfoComponent))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void constructorThrowsIfRequestInfoComponentIsNull() {
        // GIVEN: a null RequestInfoComponent
        // WHEN: constructing AppBean with null RequestInfoComponent
        // THEN: a NullPointerException is thrown
        Assertions.assertThatThrownBy(() -> new com.bestpractice.api.app.AppBean(authComponent, interceptorController, null))
                .isInstanceOf(NullPointerException.class);
    }
}
