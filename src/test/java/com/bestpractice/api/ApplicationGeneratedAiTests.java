package com.bestpractice.api;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.SpringApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ApplicationGeneratedAiTests {

    private Application application;

    @BeforeEach
    void setUp() {
        application = new Application();
    }

    @Test
    void testApplicationInstantiation() {
        // GIVEN
        Application appInstance;

        // WHEN
        appInstance = new Application();

        // THEN
        assertNotNull(appInstance);
    }

    @Test
    void testApplicationClassMetadata() {
        // GIVEN
        String expectedPackage = "com.bestpractice.api";

        // WHEN
        String actualPackage = Application.class.getPackageName();

        // THEN
        assertEquals(expectedPackage, actualPackage);
    }

    @Test
    void testMainMethodThrowsIllegalArgumentExceptionWhenArgsIsNull() {
        // GIVEN
        String[] args = null;

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> Application.main(args));
    }

    @Test
    void testMainMethodThrowsExceptionForInvalidProperty() {
        // GIVEN
        String[] args = new String[]{"--invalid.property"};

        // WHEN & THEN
        assertThrows(Exception.class, () -> Application.main(args));
    }

    @Test
    void testMainMethodWithEmptyArgsBoundaryCase() {
        // GIVEN
        String[] args = new String[]{};

        // WHEN & THEN
        assertThrows(Exception.class, () -> Application.main(args));
    }

    @Test
    void testMainMethodWithSingleValidArgBoundaryCase() {
        // GIVEN
        String[] args = new String[]{"--spring.main.banner-mode=off"};

        // WHEN & THEN
        assertThrows(Exception.class, () -> Application.main(args));
    }

    @Test
    void testMainMethodWithWhitespaceArgBoundaryCase() {
        // GIVEN
        String[] args = new String[]{" "};

        // WHEN & THEN
        assertThrows(Exception.class, () -> Application.main(args));
    }

    @Test
    void testMainMethodWithMultipleArgsBoundaryCase() {
        // GIVEN
        String[] args = new String[]{"--debug", "--spring.main.web-application-type=none"};

        // WHEN & THEN
        assertThrows(Exception.class, () -> Application.main(args));
    }

    @Test
    void testMainMethodWithDuplicateArgsBoundaryCase() {
        // GIVEN
        String[] args = new String[]{"--debug", "--debug"};

        // WHEN & THEN
        assertThrows(Exception.class, () -> Application.main(args));
    }

    @Test
    void testMainMethodWithReversedArgsBoundaryCase() {
        // GIVEN
        String[] args = new String[]{"--spring.main.web-application-type=none", "--debug"};

        // WHEN & THEN
        assertThrows(Exception.class, () -> Application.main(args));
    }

    @Test
    void testMainMethodWithLongArgsBoundaryCase() {
        // GIVEN
        String[] args = new String[]{"--spring.application.name=" + "A".repeat(1000)};

        // WHEN & THEN
        assertThrows(Exception.class, () -> Application.main(args));
    }
}
